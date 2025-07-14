package com.example.pruebasgooglemaps.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.pruebasgooglemaps.presentation.map.MapViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapScreen(viewModel: MapViewModel) {
    val userLocation by viewModel.location.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchUserLocation()
    }
    val cameraPositionState = rememberCameraPositionState {
        position = userLocation?.let {
            CameraPosition.fromLatLngZoom(LatLng(it.latitude, it.longitude), 15f)
        } ?: CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 1f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        userLocation?.let {
            Marker(
                state = rememberMarkerState(position = LatLng(it.latitude, it.longitude)),
                title = "Tu ubicación",
                snippet = "Estas Aquí!!"
            )
        }
    }
}


//    // Coordenadas de Madrid
//    val madrid = LatLng(40.4168, -3.7038)
//
//    // Configuración de la cámara del mapa
//    val cameraPositionState = rememberCameraPositionState {
//        position = CameraPosition.fromLatLngZoom(madrid, 12f)
//    }
//
//    // Componente de Google Maps
//    GoogleMap(
//        modifier = Modifier.fillMaxSize(),
//        cameraPositionState = cameraPositionState
//    ) {
//        // Marcador en Madrid
//        Marker(
//            state = rememberMarkerState(position = madrid),
//            title = "Madrid",
//            snippet = "Capital de España"
//        )
//    }

