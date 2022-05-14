package com.lovetocode.diseasesymptoms.koin

import com.montymobile.interfaces.ApiInterface
import org.koin.dsl.module
import retrofit2.Retrofit

val apiService = module {
    fun getApiService(retrofit: Retrofit) = retrofit.create(ApiInterface::class.java)

    single {
        getApiService(get())
    }
}