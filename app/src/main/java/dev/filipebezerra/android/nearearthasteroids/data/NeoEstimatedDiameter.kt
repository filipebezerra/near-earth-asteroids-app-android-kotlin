package dev.filipebezerra.android.nearearthasteroids.data

import com.squareup.moshi.Json

data class NeoEstimatedDiameter(@Json(name = "meters") val meters: NeoDiameterRange? = null)