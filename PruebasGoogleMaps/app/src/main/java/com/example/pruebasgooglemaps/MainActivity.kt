package com.example.pruebasgooglemaps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pruebasgooglemaps.ui.theme.PruebasGoogleMapsTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebasGoogleMapsTheme {
                MapScreen()
            }
        }
    }
}

@Composable
fun MapScreen() {
    // Coordenadas de Madrid
    val madrid = LatLng(40.4168, -3.7038)

    // Configuración de la cámara del mapa
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(madrid, 12f)
    }

    // Componente de Google Maps
    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // Marcador en Madrid
        Marker(
            state = rememberMarkerState(position = madrid),
            title = "Madrid",
            snippet = "Capital de España"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MapPreview() {
    PruebasGoogleMapsTheme {
        MapScreen()
    }
}
