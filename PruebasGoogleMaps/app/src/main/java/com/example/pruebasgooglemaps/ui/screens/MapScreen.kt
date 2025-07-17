package com.example.pruebasgooglemaps.ui.screens

import androidx.compose.runtime.*
import com.example.pruebasgooglemaps.presentation.map.MapViewModel
import com.example.pruebasgooglemaps.ui.components.GoogleMapComponent
import com.example.pruebasgooglemaps.data.model.LocationModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MapScreen(viewModel: MapViewModel) {
    val permisoUbicacion = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
    val userLocation by viewModel.location.collectAsState()

    // PRUEBA: Lista hardcodeada de farmacias/puntos de interés
    val farmacias = listOf(
        LocationModel("Farmacia Central", 41.5340, 2.1806),
        LocationModel("Farmacia 24h", 41.5366, 2.1850)
    )

    // Pedimos permiso de ubicación y la ubicacion solo si aún no la tenemos
    LaunchedEffect(permisoUbicacion.status) {
        if (permisoUbicacion.status.isGranted) {
            viewModel.fetchUserLocation()
        } else {
            permisoUbicacion.launchPermissionRequest()
        }
    }

    // Pasamos la ubicación del usuario y los marcadores a tu GoogleMapComponent
    GoogleMapComponent(
        userLocation = userLocation,
        markers = farmacias
    )
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

