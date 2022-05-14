package com.lovetocode.diseasesymptoms.koin

import org.koin.dsl.module

val allModule = listOf(
    commonModule,
    apiService,
    retrofitModule
)