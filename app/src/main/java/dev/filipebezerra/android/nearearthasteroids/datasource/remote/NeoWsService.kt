package dev.filipebezerra.android.nearearthasteroids.datasource.remote

import dev.filipebezerra.android.nearearthasteroids.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

const val NEO_BASE_API_URL = "https://api.nasa.gov/neo/rest/v1/"

interface NeoWsService {
    @GET("feed/today")
    suspend fun retrieveTodayNearEarthObjects(
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY,
        @Query("detailed") detailed: Boolean = true,
    ): NeoFeed

    @GET("feed")
    suspend fun retrieveNearEarthObjects(
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY,
        @Query("detailed") detailed: Boolean = true,
    ): NeoFeed
}
