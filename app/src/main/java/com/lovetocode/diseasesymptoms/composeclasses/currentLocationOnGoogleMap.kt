package com.lovetocode.diseasesymptoms.composeclasses

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun currentLocationOnGoogleMap()
{
    var cameraPositionState = rememberCameraPositionState()
    GoogleMap(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        , cameraPositionState = cameraPositionState
    )
    {
        Marker(position = LatLng(33.6844, 73.0479)
            , title = "Pakistan"
            , snippet = "Your are in pak")
        cameraPositionState.move(CameraUpdateFactory.newLatLng(LatLng(33.6844, 73.0479)))
    }
}