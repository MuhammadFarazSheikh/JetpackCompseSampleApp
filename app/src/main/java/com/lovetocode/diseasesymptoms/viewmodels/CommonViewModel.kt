package com.lovetocode.diseasesymptoms.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.lovetocode.diseasesymptoms.repositories.CommonRepositry

class CommonViewModel(
    var commonRepositry: CommonRepositry
    ): ViewModel() {
    fun getCovidUpdatesByCountryName(name:String)= liveData{
        emit(commonRepositry.getCovidUpdatesByCountryName(name))
    }

    fun getCurrentWeather(name:String)= commonRepositry.getCurrentWeather(name)
    fun getFiveDaysWeather(name:String)= commonRepositry.getFiveDaysWeather(name)
}