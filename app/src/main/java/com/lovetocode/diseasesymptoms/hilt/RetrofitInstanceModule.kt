package com.lovetocode.diseasesymptoms.hilt

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
    @WeatherUpdatesBaseUrl
    @Singleton
    @Provides
    fun getRetrofitInstanceWeatherUpdates()= buildApiServiceForWeatherUpdates()
}