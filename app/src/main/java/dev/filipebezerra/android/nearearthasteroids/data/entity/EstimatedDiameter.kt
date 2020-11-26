package dev.filipebezerra.android.nearearthasteroids.data.entity

import com.squareup.moshi.Json

data class EstimatedDiameter(
    val meters: DiameterRange
)

data class DiameterRange(
    @Json(name = "estimated_diameter_min") val minimumDiameter: Double,
    @Json(name = "estimated_diameter_max") val maximumDiameter: Double,
)
