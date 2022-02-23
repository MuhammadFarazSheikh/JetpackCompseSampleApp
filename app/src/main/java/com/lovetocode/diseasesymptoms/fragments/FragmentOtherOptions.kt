package com.lovetocode.diseasesymptoms.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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
import kotlin.reflect.KProperty

@AndroidEntryPoint
class FragmentOtherOptions : Fragment() {

    lateinit var weatherData:MutableState<Any>
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
    }

    @OptIn(ExperimentalPagerApi::class)
    @Preview
    @Composable
    fun mainContent()
    {
        weatherData = remember{ mutableStateOf(Any())}
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
                    0,1->{
                       searchWeather(this@FragmentOtherOptions,weatherData)
                    }
                }
            }
        }
    }

    fun getWeatherDetails(weatherQuery:String)
    {
        compositeDisposable.add(
            viewModel.getData(weatherQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { onSuccess, onError ->
                    if (onSuccess != null) {
                        baseBO = onSuccess
                        weatherData.value = baseBO
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