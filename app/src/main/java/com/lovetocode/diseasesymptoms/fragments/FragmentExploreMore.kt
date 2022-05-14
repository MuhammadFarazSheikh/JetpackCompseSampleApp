package com.lovetocode.diseasesymptoms.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.lovetocode.diseasesymptoms.composeclasses.*
import com.lovetocode.diseasesymptoms.models.BaseBO
import com.lovetocode.diseasesymptoms.viewmodels.CommonViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FragmentExploreMore : Fragment() {

    lateinit var weatherData:MutableState<Any>
    lateinit var fiveDaysWeatherData:MutableState<Any>
    private lateinit var baseBO: BaseBO
    val viewModel: CommonViewModel by viewModels()
    private lateinit var composeView: ComposeView
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        composeView=ComposeView(requireContext())
        return composeView.apply {
            setContent {
                mainContent()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInstances()
        getFiveDaysWeatherData()
    }

    @OptIn(ExperimentalPagerApi::class)
    @Preview
    @Composable
    fun mainContent()
    {
        weatherData = remember{ mutableStateOf(Any())}
        fiveDaysWeatherData = remember{ mutableStateOf(Any())}
        var pagerState = rememberPagerState(initialPage = 0)
        val scope = rememberCoroutineScope()
        Scaffold(topBar = {
            TabHome(selectedTabIndex =pagerState.currentPage , onSelectedTabPage ={
                scope.launch {
                    pagerState.animateScrollToPage(it.ordinal)
                }
            } )
        }) {
            HorizontalPager(state = pagerState, count = TabPage.values().size) {index->
                when(index)
                {
                    0-> showFiveDaysWeather(fiveDaysWeatherData)
                    1-> searchWeather(this@FragmentExploreMore,weatherData)
                    2-> currentLocationOnGoogleMap()
                }
            }
        }
    }

    fun getFiveDaysWeatherData()
    {
        compositeDisposable.add(
            viewModel.getFiveDaysWeather("Pakistan")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { onSuccess, onError ->
                    if (onSuccess != null) {
                        fiveDaysWeatherData.value = onSuccess
                    } else if (onError != null) {
                        fiveDaysWeatherData.value = Any()
                    }
                }
        )
    }

    fun getWeatherDetails(weatherQuery:String)
    {
        compositeDisposable.add(
            viewModel.getCurrentWeather(weatherQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { onSuccess, onError ->
                    if (onSuccess != null) {
                        baseBO = onSuccess
                        weatherData.value = baseBO
                    } else if (onError != null) {
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