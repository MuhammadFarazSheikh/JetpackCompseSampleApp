package com.lovetocode.diseasesymptoms

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.lovetocode.diseasesymptoms.repositories.CommonRepositry
import com.lovetocode.diseasesymptoms.utils.getOrWaitData
import com.lovetocode.diseasesymptoms.viewmodels.CommonViewModel
import com.montymobile.callsignature.networking.Resource
import com.montymobile.callsignature.networking.buildApiServiceForWeatherUpdates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InOrder
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.verification.VerificationMode
import java.lang.Exception
import java.util.concurrent.CountDownLatch

@RunWith(AndroidJUnit4::class)
class ViewModelsTestCases {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var commonViewModel: CommonViewModel

    @Mock
    private lateinit var commonRepositry: CommonRepositry

    @Before
    fun init() {
        MockitoAnnotations.openMocks(this)
        commonViewModel = CommonViewModel(commonRepositry)
    }

    @Test
    fun check_api_response() {
        /*commonViewModel
            .getOrWaitData()
            .let {
                when(it)
                {
                    is Resource.Success->
                    {
                    }
                    is Resource.Failure->
                    {
                    }
                    is Resource.Loading->
                    {
                    }
                }
            }*/
        //commonViewModel.dataState()
        //Mockito.verify(commonRepositry,times(2)).data()
        //Mockito.verify(commonRepositry, atLeast(1)).data()
        //Mockito.verify(commonRepositry, atMost(1)).data()
        //Mockito.verify(commonRepositry, atMostOnce()).data()
        //Mockito.verify(commonRepositry, never()).data()
        //Mockito.verifyNoMoreInteractions(commonRepositry)
        //Mockito.verifyNoInteractions(commonRepositry)
        //Mockito.doThrow(Exception()).`when`(commonRepositry)
        //Mockito.`when`(commonViewModel.data()).thenThrow(Exception()).thenReturn(false)
        //var Order = inOrder(commonRepositry)
        //Order.verify(commonRepositry).data()
        //Order.verify(commonRepositry).data()
    }
}