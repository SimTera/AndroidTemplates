package com.example.pruebasgooglemaps.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.pruebasgooglemaps.data.model.LocationModel
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.android.gms.maps.CameraUpdateFactory
import androidx.compose.ui.graphics.Color


@Composable
fun GoogleMapComponent(
    userLocation: LocationModel?, // ubicación del usuario o null
    markers: List<LocationModel>, // tiendas o puntos de interés
    defaultPosition: LatLng = LatLng(41.5348, 2.1826), // Santa Perpètua de Mogoda
    defaultZoom: Float = 15f
) {
    val cameraPositionState = rememberCameraPositionState()

    // La cámara se centrará en la ubicación del usuario si existe, si no al sitio por defecto
    val targetLatLng = userLocation?.let { LatLng(it.latitude, it.longitude) } ?: defaultPosition

    // Centrar solo cuando cambia la ubicación "principal"
    LaunchedEffect(targetLatLng) {
        cameraPositionState.animate(CameraUpdateFactory.newLatLngZoom(targetLatLng, defaultZoom))
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // Marcador especial para la ubicación personal
        userLocation?.let {
            Marker(
                state = MarkerState(LatLng(it.latitude, it.longitude)),
                title = "Tu posición",
                snippet = "Estas aquí."
            )
        }
        // Marcadores de tiendas o puntos de interés
        markers.forEach { location ->
            Marker(
                state = MarkerState(LatLng(location.latitude, location.longitude)),
                title = location.name,
            )
        }
    }
}
