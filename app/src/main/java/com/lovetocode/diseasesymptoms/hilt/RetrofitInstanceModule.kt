package com.lovetocode.diseasesymptoms.hilt

import android.content.Context
import androidx.room.Room
import com.lovetocode.diseasesymptoms.room.RoomDB
import com.montymobile.callsignature.networking.buildApiServiceForWeatherUpdates
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstanceModule
{
    @Singleton
    @Provides
    fun getRetrofitInstanceWeatherUpdates()= buildApiServiceForWeatherUpdates()
}