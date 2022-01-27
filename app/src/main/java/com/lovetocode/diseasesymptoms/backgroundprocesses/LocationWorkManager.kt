package com.lovetocode.diseasesymptoms.backgroundprocesses

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.util.Log
import androidx.work.ForegroundInfo
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.google.android.gms.location.*
import com.google.common.util.concurrent.ListenableFuture
import com.lovetocode.diseasesymptoms.utils.PreferenceDataStoreUtils
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.suspendCoroutine

class LocationWorkManager(var context: Context, var parameters: WorkerParameters) :
    Worker(context, parameters) {

    @SuppressLint("MissingPermission")
    override fun doWork(): Result {
        LocationServices.getFusedLocationProviderClient(context).apply {
            requestLocationUpdates(
                LocationRequest.create().apply {
                    setInterval(1000)
                    setFastestInterval(1000)
                    setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                },object: LocationCallback()
                {
                    override fun onLocationResult(locationResult: LocationResult) {
                        super.onLocationResult(locationResult)

                        locationResult.let {
                            /*PreferenceDataStoreUtils
                                .saveLocationDataData(
                                context,
                                    it.locations.get(0).latitude.toString(),
                                    it.locations.get(0).longitude.toString())
                            removeLocationUpdates(this)*/
                        }
                    }

                    override fun onLocationAvailability(p0: LocationAvailability) {
                        super.onLocationAvailability(p0)
                    }
                } , Looper.getMainLooper())
        }

        return Result.success()
    }
}