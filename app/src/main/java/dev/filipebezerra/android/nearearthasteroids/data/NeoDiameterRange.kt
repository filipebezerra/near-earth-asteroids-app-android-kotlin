package dev.filipebezerra.android.nearearthasteroids.data

import com.squareup.moshi.Json

data class NeoDiameterRange(
    @Json(name = "estimated_diameter_min") val minimumDiameter: Double? = null,
    @Json(name = "estimated_diameter_max") val maximumDiameter: Double? = null,
)