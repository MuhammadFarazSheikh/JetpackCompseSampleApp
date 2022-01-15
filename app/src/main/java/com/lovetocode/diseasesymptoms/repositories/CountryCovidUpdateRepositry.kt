package com.lovetocode.diseasesymptoms.repositories

import com.lovetocode.diseasesymptoms.hilt.CovidUpdatesBaseUrl
import com.lovetocode.diseasesymptoms.hilt.WeatherUpdatesBaseUrl
import com.montymobile.callsignature.networking.ApiEndPoints
import com.montymobile.callsignature.repositories.BaseRepository
import com.montymobile.interfaces.ApiInterface
import javax.inject.Inject


class CountryCovidUpdateRepositry @Inject constructor(
    @CovidUpdatesBaseUrl var apiInterfaceCovidUpdates: ApiInterface,
    @WeatherUpdatesBaseUrl var apiInterfaceWeatherUpdates: ApiInterface
    ): BaseRepository() {
    suspend fun getCovidUpdatesByCountryName(name:String)=safeApiCall {
        apiInterfaceCovidUpdates.searchByName(name)
    }

    fun getData(name:String)=apiInterfaceWeatherUpdates.getData(ApiEndPoints.SEARCH_WEATHER_BY_NAME+name+ ApiEndPoints.WEATHER_API_APP_ID)
}