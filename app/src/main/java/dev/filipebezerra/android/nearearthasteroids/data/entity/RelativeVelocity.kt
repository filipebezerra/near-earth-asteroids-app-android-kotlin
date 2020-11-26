package dev.filipebezerra.android.nearearthasteroids.data.entity

import com.squareup.moshi.Json

data class RelativeVelocity(@Json(name = "kilometers_per_second") val kilometersPerSecond: Double)
