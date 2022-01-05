package com.lovetocode.diseasesymptoms.repositories

import com.montymobile.callsignature.repositories.BaseRepository
import com.montymobile.interfaces.ApiInterface
import javax.inject.Inject


class CountryCovidUpdateRepositry @Inject constructor(var apiInterface: ApiInterface): BaseRepository()
{
    suspend fun getCovidUpdatesByCountryName(name:String)=safeApiCall {
        apiInterface.searchByName(name)
    }
}