package dev.filipebezerra.android.nearearthasteroids.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.filipebezerra.android.nearearthasteroids.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_API_URL = "https://api.nasa.gov/neo/rest/v1/"

private val moshiBuilder = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())

private val okHttpClientBuilder = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply { level = BODY })
//    .addNetworkInterceptor()
//    .readTimeout()
//    .callTimeout()
//    .cache()

private val retrofit = Retrofit.Builder()
    .baseUrl(BASE_API_URL)
    .addConverterFactory(MoshiConverterFactory.create(moshiBuilder.build()))
    .client(okHttpClientBuilder.build())
    .build()

interface NeoWsService {
    @GET("feed/today")
    suspend fun retrieveNearEarthObjects(
        @Query("api_key") apiKey: String = BuildConfig.NEOWS_API_KEY,
        @Query("detailed") detailed: Boolean = true,
    ): NeoFeed
}

object NeoWsApi {
    val service: NeoWsService by lazy { retrofit.create(NeoWsService::class.java) }
}

