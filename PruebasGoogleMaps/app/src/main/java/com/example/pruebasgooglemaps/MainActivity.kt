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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
import com.example.pruebasgooglemaps.data.repository.LocationRepository

class MainActivity : ComponentActivity() {

    private val mapViewModel: MapViewModel by lazy {
        ViewModelProvider(this, MapViewModelFactory(LocationRepository(applicationContext)))[MapViewModel::class.java]
    }

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

class MapViewModelFactory(
    private val myRepository: LocationRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MapViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MapViewModel(myRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

// -----------------------------
// He de ver como hago la preview para ver como esta todo montado que no funciona
// -----------------------------


