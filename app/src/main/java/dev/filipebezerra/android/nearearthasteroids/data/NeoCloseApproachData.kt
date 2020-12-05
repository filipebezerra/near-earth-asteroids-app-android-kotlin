package dev.filipebezerra.android.nearearthasteroids.data

import com.squareup.moshi.Json

data class NeoCloseApproachData (
    @Json(name = "close_approach_date") val approachDate: String? = null,
    @Json(name = "epoch_date_close_approach") val approachEpochDate: Long? = null,
    @Json(name = "relative_velocity") val relativeVelocity: NeoRelativeVelocity? = null,
    @Json(name = "miss_distance") val missDistance: NeoMissDistance? = null,
)