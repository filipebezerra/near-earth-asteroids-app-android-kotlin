package dev.filipebezerra.android.nearearthasteroids.data.source.remote

import com.squareup.moshi.Json
import dev.filipebezerra.android.nearearthasteroids.data.NearEarthObject

data class NeoFeed(
    @Json(name = "element_count") val elementCount: Int,
    @Json(name = "near_earth_objects") val asteroidsByDate: Map<String, List<NearEarthObject>>
)