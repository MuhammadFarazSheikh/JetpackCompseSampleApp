package com.montymobile.interfaces

import com.lovetocode.diseasesymptoms.models.BaseBO
import com.lovetocode.diseasesymptoms.models.WeatherMainBO
import com.montymobile.callsignature.networking.ApiEndPoints
import retrofit2.http.*

interface ApiInterface
{
    @GET(ApiEndPoints.SEARCH_WEATHER_BY_NAME)
    suspend fun getData(@Query("q") name:String,@Query("APPID") appId:String):BaseBO

    @GET(ApiEndPoints.FIVE_DAYS_WEATHER)
    suspend fun getFiveDaysData(@Query("q") name:String,@Query("APPID") appId:String):WeatherMainBO
}