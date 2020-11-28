package dev.filipebezerra.android.nearearthasteroids.data.entity

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class CloseApproachData(
    @Json(name = "close_approach_date") val approachDate: String,
    @Json(name = "epoch_date_close_approach") val approachEpochDate: Long,
    @Json(name = "relative_velocity") val relativeVelocity: RelativeVelocity,
    @Json(name = "miss_distance") val missDistance: MissDistance,
) : Parcelable