package dev.filipebezerra.android.nearearthasteroids.data.entity

import com.squareup.moshi.Json

data class NearEarthObject(
    val id: String,
    val name: String,
    @Json(name = "nasa_jpl_url") val nasaJplUrl: String,
    @Json(name = "absolute_magnitude_h") val absoluteMagnitude: Float,
    @Json(name = "estimated_diameter") val estimatedDiameter: EstimatedDiameter,
    @Json(name = "is_potentially_hazardous_asteroid") val isPotentiallyHazardousAsteroid: Boolean,
    @Json(name = "close_approach_data") val closeApproachData: List<CloseApproachData>,
    @Json(name = "orbital_data") val orbitalData: OrbitalData,
)