package com.lovetocode.diseasesymptoms.utils

import android.content.Context
import android.location.Address
import android.location.Geocoder
import com.google.android.gms.maps.model.LatLng
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
    }
}