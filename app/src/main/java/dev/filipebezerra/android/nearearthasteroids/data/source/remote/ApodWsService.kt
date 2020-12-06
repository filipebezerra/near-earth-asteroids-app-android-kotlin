package dev.filipebezerra.android.nearearthasteroids.data.source.remote

import dev.filipebezerra.android.nearearthasteroids.BuildConfig
import dev.filipebezerra.android.nearearthasteroids.util.LocalDateExt.dateNowFormatted
import retrofit2.http.GET
import retrofit2.http.Query

interface ApodWsService {
    @GET("apod")
    suspend fun retrievePictureOfTheDay(
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY,
        @Query("hd") highResolutionImage: Boolean = true,
        @Query("date") dateOfTheImage: String = dateNowFormatted()
    ): PictureOfTheDay
}
