package com.lovetocode.diseasesymptoms.koin

import com.montymobile.callsignature.networking.buildApiServiceForCovidUpdates
import com.montymobile.callsignature.networking.buildApiServiceForWeatherUpdates
import org.koin.dsl.module

val retrofitModule = module {
    single {
        buildApiServiceForCovidUpdates()
    }

    single {
        buildApiServiceForWeatherUpdates()
    }
}