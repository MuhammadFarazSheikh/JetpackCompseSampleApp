package com.lovetocode.diseasesymptoms.composeclasses

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.lovetocode.diseasesymptoms.models.BaseBO
import com.lovetocode.diseasesymptoms.models.WeatherMainBO

@OptIn(ExperimentalPagerApi::class)
@Composable
fun showFiveDaysWeather(mutableState: MutableState<Any>)
{
    if(mutableState.value is WeatherMainBO)
    {
        val weatherMainBO = mutableState.value as WeatherMainBO
        val pagerState = rememberPagerState(initialPage = 0)
        HorizontalPager(
            state = pagerState,
            count = weatherMainBO.list.size,
            modifier = Modifier.fillMaxWidth().wrapContentHeight() ) { currentPage->
            showWeatherData(baseBO = weatherMainBO.list.get(currentPage) as BaseBO)
        }
    }
}