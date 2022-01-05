package com.montymobile.callsignature

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.montymobile.callsignature.networking.buildApiService
import com.montymobile.videorbt.utils.SharedPreferenceUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application()
{
    companion object {

        val apiService by lazy {
            buildApiService()
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        MultiDex.install(this)
    }
}