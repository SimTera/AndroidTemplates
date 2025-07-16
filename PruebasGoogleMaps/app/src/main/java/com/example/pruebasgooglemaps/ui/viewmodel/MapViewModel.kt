package com.example.pruebasgooglemaps.presentation.map

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.pruebasgooglemaps.data.model.LocationModel
import com.example.pruebasgooglemaps.data.repository.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

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

class MapViewModel(private val locationRepository: LocationRepository) : ViewModel() {

    private val _location = MutableStateFlow<LocationModel?>(null)
    val location: StateFlow<LocationModel?> = _location

    fun fetchUserLocation() {
        viewModelScope.launch {
            val loc = locationRepository.getLastKnownLocation()
            _location.value = loc
        }
    }
}
