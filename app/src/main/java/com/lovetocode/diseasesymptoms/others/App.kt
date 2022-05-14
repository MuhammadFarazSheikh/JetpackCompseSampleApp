package com.lovetocode.diseasesymptoms.others

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.lovetocode.diseasesymptoms.koin.allModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application()
{
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            allModule
        }
    }
}