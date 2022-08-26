package com.lovetocode.diseasesymptoms.repositories

import com.montymobile.callsignature.networking.ApiEndPoints
import com.montymobile.callsignature.repositories.BaseRepository
import com.montymobile.interfaces.ApiInterface
import javax.inject.Inject


open class CommonRepositry @Inject constructor(
    var apiInterfaceWeatherUpdates: ApiInterface
    ): BaseRepository() {

    suspend fun getData(name:String)=safeApiCall {
        apiInterfaceWeatherUpdates.getData(name,ApiEndPoints.WEATHER_API_APP_ID)
    }

    suspend fun getFiveDaysData(name:String)=safeApiCall {
        apiInterfaceWeatherUpdates.getFiveDaysData(name,ApiEndPoints.WEATHER_API_APP_ID)
    }

    open fun data():Boolean{
        return true
    }

    open fun dataState(){

    }
}