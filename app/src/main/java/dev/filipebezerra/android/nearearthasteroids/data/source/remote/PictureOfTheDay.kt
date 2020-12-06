package dev.filipebezerra.android.nearearthasteroids.data.source.remote

import com.squareup.moshi.Json

data class PictureOfTheDay(
    @Json(name = "date") val date: String? = null,
    @Json(name = "explanation") val explanation: String? = null,
    @Json(name = "media_type") val mediaType: String? = null,
    @Json(name = "url") val pictureUrl: String? = null,
    @Json(name = "hdurl") val hdPictureUrl: String? = null,
)