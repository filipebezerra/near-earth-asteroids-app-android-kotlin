package dev.filipebezerra.android.nearearthasteroids.data.entity

data class Asteroid(
    val id: String,
    val name: String,
    val nasaJplUrl: String,
    val absoluteMagnitude: Float,
    val estimatedDiameter: EstimatedDiameter,
    val isPotentiallyHazardousAsteroid: Boolean,
    val closeApproachData: List<CloseApproachData>,
    val orbitalData: OrbitalData,
)