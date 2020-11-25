package dev.filipebezerra.android.nearearthasteroids.data.entity

data class EstimatedDiameter(
    val meters: EstimatedDiameterInMeters
)

data class EstimatedDiameterInMeters(
    val minimum: Double,
    val maximum: Double,
)
