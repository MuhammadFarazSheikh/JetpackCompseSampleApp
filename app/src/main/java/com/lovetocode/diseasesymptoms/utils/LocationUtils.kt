package com.lovetocode.diseasesymptoms.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Looper
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.lovetocode.diseasesymptoms.interfaces.OnLocationSelected
import kotlinx.coroutines.runBlocking
import java.io.IOException
import java.util.*


class LocationUtils
{
    companion object
    {
        fun getCountryNameByLocation(context: Context,latLng: LatLng):String
        {
            val geocoder = Geocoder(context, Locale.getDefault())
            try {
                var addresses: List<Address> = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
                return addresses.let {
                    addresses[0].getCountryName()
                }
            } catch (ignored: IOException) {
                return ""
            }
        }

        @SuppressLint("MissingPermission")
        fun getLiveLocation(context: Context,onLocationSelected:OnLocationSelected)
        {
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
                                runBlocking {
                                    PreferenceDataStoreUtils
                                        .saveLocationDataData(
                                            context,
                                            it.locations.get(0).latitude.toString(),
                                            it.locations.get(0).longitude.toString())
                                }
                                onLocationSelected.onLocationSelected()
                                removeLocationUpdates(this)
                            }
                        }

                        override fun onLocationAvailability(p0: LocationAvailability) {
                            super.onLocationAvailability(p0)
                        }
                    } , Looper.getMainLooper())
            }
        }
    }
}