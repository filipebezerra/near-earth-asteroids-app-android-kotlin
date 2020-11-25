package dev.filipebezerra.android.nearearthasteroids.data.entity

data class CloseApproachData(
    val approachDate: String,
    val approachDateFull: String,
    val relativeVelocity: RelativeVelocity,
    val missDistance: MissDistance,
)