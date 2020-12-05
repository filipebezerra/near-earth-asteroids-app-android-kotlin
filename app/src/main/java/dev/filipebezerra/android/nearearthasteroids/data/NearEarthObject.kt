package dev.filipebezerra.android.nearearthasteroids.data

import com.squareup.moshi.Json

data class NearEarthObject(
    @Json(name = "id") val id: String? = null,
    @Json(name = "name") val name: String? = null,
    @Json(name = "nasa_jpl_url") val nasaJplUrl: String? = null,
    @Json(name = "absolute_magnitude_h") val absoluteMagnitude: Float? = null,
    @Json(name = "estimated_diameter") val estimatedDiameter: NeoEstimatedDiameter? = null,
    @Json(name = "is_potentially_hazardous_asteroid") val isPotentiallyHazardousAsteroid: Boolean? = null,
    @Json(name = "close_approach_data") val closeApproachData: List<NeoCloseApproachData>? = null,
    @Json(name = "orbital_data") val orbitalData: NeoOrbitalData? = null,
)