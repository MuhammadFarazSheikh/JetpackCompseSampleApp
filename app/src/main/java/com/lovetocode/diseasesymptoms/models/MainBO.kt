package com.lovetocode.diseasesymptoms.models

data class MainBO (
    val temp:String,
    val temp_min:String,
    val temp_max:String,
    val pressure:String,
    val sea_level:String,
    val grnd_level:String,
    val humidity:String,
    val temp_kf:String
)