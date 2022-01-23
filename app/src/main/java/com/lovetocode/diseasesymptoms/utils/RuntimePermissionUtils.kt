package com.lovetocode.diseasesymptoms.utils

import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat

class RuntimePermissionUtils
{
    companion object
    {
        fun checkIfLocationPermissionsGranted(context: Context):Boolean
        {
            return (ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED
                    &&
                    ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)

        }

        fun grantLocationPermission(activityResultLauncher: ActivityResultLauncher<Array<String>>)
        {
            activityResultLauncher.launch(arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION))
        }
    }
}