package com.example.pruebasgooglemaps

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pruebasgooglemaps.presentation.map.MapViewModel
import com.example.pruebasgooglemaps.ui.theme.PruebasGoogleMapsTheme
import com.example.pruebasgooglemaps.ui.screens.MapScreen
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.example.pruebasgooglemaps.data.model.LocationModel

class MainActivity : ComponentActivity() {

    private val mapViewModel: MapViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PruebasGoogleMapsTheme {
                MapScreen(viewModel = mapViewModel) // Primera pantalla a mostrar
            }
        }
    }
}

// -----------------------------
// He de ver como hago la preview para ver como esta todo montado que no funciona
// -----------------------------


