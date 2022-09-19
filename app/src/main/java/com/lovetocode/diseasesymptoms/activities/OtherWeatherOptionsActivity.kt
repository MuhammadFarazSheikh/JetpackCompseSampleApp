package com.lovetocode.diseasesymptoms.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.lovetocode.diseasesymptoms.composeclasses.*
import com.lovetocode.diseasesymptoms.models.BaseBO
import com.lovetocode.diseasesymptoms.models.WeatherMainBO
import com.lovetocode.diseasesymptoms.viewmodels.CommonViewModel
import com.montymobile.callsignature.networking.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class OtherWeatherOptionsActivity : AppCompatActivity() {
    lateinit var weatherData: MutableState<Any>
    lateinit var fiveDaysWeatherData: MutableState<Any>
    val viewModel: CommonViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            mainContent()
        }
        getFiveDaysData()
    }

    @OptIn(ExperimentalPagerApi::class)
    @Preview
    @Composable
    fun mainContent() {
        weatherData = remember { mutableStateOf(Any()) }
        fiveDaysWeatherData = remember { mutableStateOf(Any()) }
        var pagerState = rememberPagerState(initialPage = 0)
        val scope = rememberCoroutineScope()
        Scaffold(topBar = {
            TabHome(selectedTabIndex = pagerState.currentPage, onSelectedTabPage = {
                scope.launch {
                    pagerState.animateScrollToPage(it.ordinal)
                }
            })
        }) {
            HorizontalPager(state = pagerState, count = TabPage.values().size) { index ->
                when (index) {
                    0 -> {
                        showFiveDaysWeather(fiveDaysWeatherData)
                    }
                    1 -> {
                        searchWeather(this@OtherWeatherOptionsActivity, weatherData)
                    }
                    2-> {
                        showMap()
                    }
                }
            }
        }
    }

    fun getFiveDaysData() {
        viewModel
            .getFiveDaysData("Pakistan").observe(this, Observer {
                when(it)
                {
                    is Resource.Success->
                    {
                        var tempArray = ArrayList<BaseBO>()

                        var tempDate = ""

                        tempDate = it.value.list.get(0).dt_txt.split(" ")[0]

                        for (i in it.value.list) {
                            if (!tempDate.equals(i.dt_txt.split(" ")[0])) {
                                var arrayTemp = ArrayList<BaseBO>()

                                for (j in it.value.list) {
                                    if (j != null && j.dt_txt.split(" ")[0].equals(i.dt_txt.split(" ")[0])) {
                                        arrayTemp.add(j)
                                    }
                                }
                                i.arrayList = ArrayList()
                                i.arrayList.addAll(arrayTemp)
                                tempArray.add(i)
                                tempDate = i.dt_txt.split(" ")[0]
                            }
                        }

                        fiveDaysWeatherData.value=WeatherMainBO(tempArray)
                    }
                    is Resource.Failure->
                    {
                        it
                    }
                    is Resource.Loading->
                    {

                    }
                }
            })
    }

    fun getWeatherDetails(weatherQuery: String) {
        viewModel.getData(weatherQuery).observe(this, Observer {
            when(it)
            {
                is Resource.Success->
                {
                    weatherData.value=it.value
                }
                is Resource.Failure->
                {
                    it
                }
                is Resource.Loading->
                {

                }
            }
        })
    }
}