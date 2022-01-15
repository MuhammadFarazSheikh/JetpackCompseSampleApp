package com.lovetocode.diseasesymptoms.hilt

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class CovidUpdatesBaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WeatherUpdatesBaseUrl