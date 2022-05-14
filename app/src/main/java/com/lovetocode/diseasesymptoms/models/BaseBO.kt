package com.lovetocode.diseasesymptoms.models

import com.ofsol.weatheralerts.models.CloudsBO
import com.ofsol.weatheralerts.models.WeatherBO
import java.util.*

data class BaseBO(
    var dt:String,
    var main: MainBO,
    var weather: ArrayList<WeatherBO>,
    var clouds: CloudsBO,
    var wind: WindBO,
    var dt_txt: String,
    var arrayList: ArrayList<BaseBO>
)