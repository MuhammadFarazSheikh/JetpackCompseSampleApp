package com.lovetocode.diseasesymptoms.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.lovetocode.diseasesymptoms.repositories.CommonRepositry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
open class CommonViewModel @Inject constructor(
    var commonRepositry: CommonRepositry
    ): ViewModel() {

    fun getData(name:String)= commonRepositry.getData(name)

    open fun data()=commonRepositry.data()
    open fun dataState()=commonRepositry.dataState()
}