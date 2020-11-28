package dev.filipebezerra.android.nearearthasteroids.data.entity

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class EstimatedDiameter(
    val meters: DiameterRange
)  : Parcelable

@Parcelize
data class DiameterRange(
    @Json(name = "estimated_diameter_min") val minimumDiameter: Double,
    @Json(name = "estimated_diameter_max") val maximumDiameter: Double,
)  : Parcelable
