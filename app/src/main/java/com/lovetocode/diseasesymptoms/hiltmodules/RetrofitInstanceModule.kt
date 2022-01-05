package com.lovetocode.diseasesymptoms.hiltmodules

import com.montymobile.callsignature.networking.buildApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitInstanceModule
{
    @Singleton
    @Provides
    fun getRetrofitInstance()= buildApiService()
}