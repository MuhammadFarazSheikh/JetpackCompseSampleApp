package com.lovetocode.diseasesymptoms.utils

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.lovetocode.diseasesymptoms.backgroundprocesses.LocationWorkManager
import java.util.*

object BackgroundProcessUtils
{
    fun startWorkManager(context: Context,lifecycleOwner: LifecycleOwner,workInfoObserver: Observer<WorkInfo>)
    {
        WorkManager.getInstance(context)
            .enqueueUniqueWork(
                "oneTimeLocation",
                ExistingWorkPolicy.REPLACE,
                OneTimeWorkRequestBuilder<LocationWorkManager>().build().apply {
                    WorkManager.getInstance(context).getWorkInfoByIdLiveData(id)
                        .observe(lifecycleOwner,workInfoObserver)
                })
    }
}