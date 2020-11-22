package dev.filipebezerra.android.nearearthasteroids.data.entity

data class EstimatedDiameter(
    val kilometers: EstimatedDiameterInKilometers
)

data class EstimatedDiameterInKilometers(val maximum: Double)
