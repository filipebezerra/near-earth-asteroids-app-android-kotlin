package dev.filipebezerra.android.nearearthasteroids.data.entity

import com.squareup.moshi.Json

data class CloseApproachData(
    @Json(name = "close_approach_date") val approachDate: String,
    @Json(name = "epoch_date_close_approach") val approachEpochDate: Long,
    @Json(name = "relative_velocity") val relativeVelocity: RelativeVelocity,
    @Json(name = "miss_distance") val missDistance: MissDistance,
)