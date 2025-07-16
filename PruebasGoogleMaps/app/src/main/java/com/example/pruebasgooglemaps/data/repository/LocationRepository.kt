package com.example.pruebasgooglemaps.data.repository

import android.content.Context
import com.example.pruebasgooglemaps.data.model.LocationModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await

class LocationRepository(
    private val context: Context
) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    suspend fun getLastKnownLocation(): LocationModel? {
        return try {
            val location = fusedLocationClient.lastLocation.await ()
            location.let {
                LocationModel(
                    name = "Tu ubicación",
                    latitude = it.latitude,
                    longitude = it.longitude
                )
            }
        } catch (e: SecurityException) {
            null // No se tienen permisos
        } catch (e: Exception) {
            null // Otros posibles problemas
        }
    }
}