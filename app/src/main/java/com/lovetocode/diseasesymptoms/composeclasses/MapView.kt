package com.lovetocode.diseasesymptoms.composeclasses

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun showMap()
{
    val latLng = LatLng(33.6844,73.0479)
    val cameraPositionState = rememberCameraPositionState(){
        position = CameraPosition.fromLatLngZoom(latLng,10f)
    }
    var uiSettings by remember { mutableStateOf(MapUiSettings()) }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = uiSettings
    )
    {
        Marker(
            state = MarkerState(latLng),
            title = "Islamabad",

        )
    }
}