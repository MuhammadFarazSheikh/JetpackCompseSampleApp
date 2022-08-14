package com.lovetocode.diseasesymptoms.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.lovetocode.diseasesymptoms.composeclasses.TabHome
import com.lovetocode.diseasesymptoms.composeclasses.TabPage
import com.lovetocode.diseasesymptoms.composeclasses.searchWeather
import com.lovetocode.diseasesymptoms.models.BaseBO
import com.lovetocode.diseasesymptoms.viewmodels.CommonViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OtherWeatherOptionsActivity : AppCompatActivity() {
    lateinit var weatherData: MutableState<Any>
    lateinit var fiveDaysWeatherData: MutableState<Any>
    val viewModel: CommonViewModel by viewModels()
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initInstances()
        setContent {
            mainContent()
        }
        getFiveDaysData()
    }

    @OptIn(ExperimentalPagerApi::class)
    @Preview
    @Composable
    fun mainContent()
    {
        weatherData = remember{ mutableStateOf(Any()) }
        var pagerState = rememberPagerState(initialPage = 0)
        val scope = rememberCoroutineScope()
        Scaffold(topBar = {
            TabHome(selectedTabIndex =pagerState.currentPage , onSelectedTabPage ={
                scope.launch {
                    pagerState.animateScrollToPage(it.ordinal)
                }
            } )
        }) {
            HorizontalPager(state = pagerState, count = TabPage.values().size) { index->
                when(index)
                {
                    0,1->{
                       searchWeather(this@OtherWeatherOptionsActivity,weatherData)
                    }
                }
            }
        }
    }

    fun getFiveDaysData()
    {
        compositeDisposable.add(
            viewModel.getFiveDaysData("Pakistan")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { onSuccess, onError ->
                    if (onSuccess != null) {
                        fiveDaysWeatherData.value = onSuccess
                    }
                    if (onError != null) {
                        weatherData.value = Any()
                    }
                }
        )
    }

    fun getWeatherDetails(weatherQuery:String)
    {
        compositeDisposable.add(
            viewModel.getData(weatherQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { onSuccess, onError ->
                    if (onSuccess != null) {
                        weatherData.value = onSuccess
                    }
                    if (onError != null) {
                        weatherData.value = Any()
                    }
                }
        )
    }

    private fun initInstances() {
        compositeDisposable = CompositeDisposable()
    }

    override fun onDestroy() {

        if (::compositeDisposable.isInitialized) {
            compositeDisposable.dispose()
        }

        super.onDestroy()
    }
}