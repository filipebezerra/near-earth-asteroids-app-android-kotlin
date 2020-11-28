package dev.filipebezerra.android.nearearthasteroids.data.entity

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class RelativeVelocity(
    @Json(name = "kilometers_per_second") val kilometersPerSecond: Double
) : Parcelable
