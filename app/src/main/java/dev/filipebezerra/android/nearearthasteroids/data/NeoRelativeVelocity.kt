package dev.filipebezerra.android.nearearthasteroids.data

import com.squareup.moshi.Json

data class NeoRelativeVelocity(@Json(name = "kilometers_per_second") val kilometersPerSecond: Double? = null)
