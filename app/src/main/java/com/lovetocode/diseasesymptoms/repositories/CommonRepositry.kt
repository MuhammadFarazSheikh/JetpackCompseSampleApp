package com.lovetocode.diseasesymptoms.repositories

import com.montymobile.callsignature.networking.ApiEndPoints
import com.montymobile.callsignature.repositories.BaseRepository
import com.montymobile.interfaces.ApiInterface

class CommonRepositry (
    var apiInterfaceCovidUpdates: ApiInterface,
    var apiInterfaceWeatherUpdates: ApiInterface
    ): BaseRepository() {

    suspend fun getCovidUpdatesByCountryName(name:String)=safeApiCall {
        apiInterfaceCovidUpdates.searchByName(name)
    }

    fun getCurrentWeather(name:String)=apiInterfaceWeatherUpdates.getCurrentWeather(ApiEndPoints.SEARCH_WEATHER_BY_NAME+name+ ApiEndPoints.WEATHER_API_APP_ID)
    fun getFiveDaysWeather(name:String)=apiInterfaceWeatherUpdates.getFiveDaysWeather(ApiEndPoints.SEARCH_WEATHER_BY_NAME_FOR_FIVE_DAYS+name+ ApiEndPoints.WEATHER_API_APP_ID)
}