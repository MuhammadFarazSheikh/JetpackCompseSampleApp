package com.lovetocode.diseasesymptoms.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.lovetocode.diseasesymptoms.repositories.CountryCovidUpdateRepositry
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CommonViewModel @Inject constructor(
    var countryCovidUpdateRepositry: CountryCovidUpdateRepositry
    ): ViewModel() {
    fun getCovidUpdatesByCountryName(name:String)= liveData{
        emit(countryCovidUpdateRepositry.getCovidUpdatesByCountryName(name))
    }

    fun getData(name:String)= countryCovidUpdateRepositry.getData(name)
}