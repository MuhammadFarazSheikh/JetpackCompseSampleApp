package com.montymobile.interfaces

import com.lovetocode.diseasesymptoms.models.CountryCovidUpdatesDAO
import com.montymobile.callsignature.networking.ApiEndPoints
import retrofit2.http.*

interface ApiInterface
{
    @Headers("x-rapidapi-key:7516e89963mshf2e2438dad34118p1ce102jsn1131cbdf9dfa")
    @GET(ApiEndPoints.SEARCH_BY_NAME)
    suspend fun searchByName(@Query("name") name:String):ArrayList<CountryCovidUpdatesDAO>
}