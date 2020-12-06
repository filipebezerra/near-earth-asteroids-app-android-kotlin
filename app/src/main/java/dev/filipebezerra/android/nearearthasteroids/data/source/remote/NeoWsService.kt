package dev.filipebezerra.android.nearearthasteroids.data.source.remote

import dev.filipebezerra.android.nearearthasteroids.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface NeoWsService {
    @GET("feed/today")
    suspend fun retrieveNearEarthObjects(
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY,
        @Query("detailed") detailed: Boolean = true,
    ): NeoFeed
}
