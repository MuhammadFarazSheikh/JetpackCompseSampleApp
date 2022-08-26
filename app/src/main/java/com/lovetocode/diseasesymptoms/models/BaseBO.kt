package com.lovetocode.diseasesymptoms.models

import com.ofsol.weatheralerts.models.CloudsBO
import com.ofsol.weatheralerts.models.WeatherBO
import java.util.*

data class BaseBO(
    val dt:String,
    val main: MainBO,
    val weather: ArrayList<WeatherBO>,
    val clouds: CloudsBO,
    val wind: WindBO,
    val dt_txt: String,
    var arrayList: ArrayList<BaseBO>
)