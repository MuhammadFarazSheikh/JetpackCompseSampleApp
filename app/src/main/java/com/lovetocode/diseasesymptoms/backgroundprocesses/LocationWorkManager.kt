package com.lovetocode.diseasesymptoms.backgroundprocesses

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.google.android.gms.location.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class LocationWorkManager(var context: Context, var parameters: WorkerParameters) :
    Worker(context, parameters) {

    @SuppressLint("MissingPermission")
    override fun doWork(): Result {
        var results:Result?=null
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
                            results=Result.success(workDataOf("Lat" to it.locations.get(0).latitude,"Long" to it.locations.get(0).longitude))
                            removeLocationUpdates(this)
                        }
                    }

                    override fun onLocationAvailability(p0: LocationAvailability) {
                        super.onLocationAvailability(p0)
                    }
                } , Looper.getMainLooper())
        }

        /*runBlocking {
            delay(5000)
        }*/

        return results!!
    }
}