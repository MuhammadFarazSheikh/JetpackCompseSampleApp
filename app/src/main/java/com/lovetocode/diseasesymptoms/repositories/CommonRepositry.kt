package com.lovetocode.diseasesymptoms.repositories

import com.montymobile.callsignature.networking.ApiEndPoints
import com.montymobile.callsignature.repositories.BaseRepository
import com.montymobile.interfaces.ApiInterface
import javax.inject.Inject


open class CommonRepositry @Inject constructor(
    var apiInterfaceWeatherUpdates: ApiInterface
    ): BaseRepository() {

    fun getData(name:String)=apiInterfaceWeatherUpdates.getData(ApiEndPoints.SEARCH_WEATHER_BY_NAME+name+ ApiEndPoints.WEATHER_API_APP_ID)

    fun getFiveDaysData(name:String)=apiInterfaceWeatherUpdates.getFiveDaysData(ApiEndPoints.FIVE_DAYS_WEATHER+name+ ApiEndPoints.WEATHER_API_APP_ID)

    open fun data():Boolean{
        return true
    }

    open fun dataState(){

    }
}