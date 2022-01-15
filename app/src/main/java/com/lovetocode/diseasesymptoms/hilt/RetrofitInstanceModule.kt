package com.lovetocode.diseasesymptoms.hilt

import com.montymobile.callsignature.networking.buildApiServiceForCovidUpdates
import com.montymobile.callsignature.networking.buildApiServiceForWeatherUpdates
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstanceModule
{
    @CovidUpdatesBaseUrl
    @Singleton
    @Provides
    fun getRetrofitInstanceCovidUpdates()= buildApiServiceForCovidUpdates()

    @WeatherUpdatesBaseUrl
    @Singleton
    @Provides
    fun getRetrofitInstanceWeatherUpdates()= buildApiServiceForWeatherUpdates()
}