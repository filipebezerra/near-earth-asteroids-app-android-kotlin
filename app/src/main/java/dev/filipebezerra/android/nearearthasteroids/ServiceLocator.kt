package dev.filipebezerra.android.nearearthasteroids

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.filipebezerra.android.nearearthasteroids.data.source.AsteroidDataSource
import dev.filipebezerra.android.nearearthasteroids.data.source.AsteroidRepository
import dev.filipebezerra.android.nearearthasteroids.data.source.DefaultAsteroidRepository
import dev.filipebezerra.android.nearearthasteroids.data.source.local.AsteroidDatabase
import dev.filipebezerra.android.nearearthasteroids.data.source.local.AsteroidDatabase.Companion.DB_NAME
import dev.filipebezerra.android.nearearthasteroids.data.source.local.AsteroidLocalDataSource
import dev.filipebezerra.android.nearearthasteroids.data.source.remote.ApodWsService
import dev.filipebezerra.android.nearearthasteroids.data.source.remote.AsteroidRemoteDataSource
import dev.filipebezerra.android.nearearthasteroids.data.source.remote.NeoWsService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val NEO_BASE_API_URL = "https://api.nasa.gov/neo/rest/v1/"
private const val APOD_BASE_API_URL = "https://api.nasa.gov/planetary/"

/**
 * A Service Locator object for the [AsteroidRepository].
 */
object ServiceLocator {

    private val lock = Any()

    private var database: AsteroidDatabase? = null

    private val moshiBuilder: Moshi.Builder by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory())
    }

    private val okHttpClientBuilder: OkHttpClient.Builder by lazy {
        OkHttpClient.Builder()
            // TODO Improve networking with passing API KEY via interceptor
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        // TODO Improve networking with Stetho for debugging
        // .addNetworkInterceptor()
        // TODO Improve networking with timeout policy
        // .readTimeout()
        // .callTimeout()
        // TODO Improve networking with caching
        // .cache()
        // TODO Improve networking with retry policy
        // .retryOnConnectionFailure()
    }

    private val retrofitBuilder: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(NEO_BASE_API_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshiBuilder.build()))
            .client(okHttpClientBuilder.build())
    }

    @Volatile
    var asteroidRepository: AsteroidRepository? = null
        @VisibleForTesting set


    @Volatile
    var neoWsService: NeoWsService? = null
        @VisibleForTesting set

    @Volatile
    var apodWsService: ApodWsService? = null
        @VisibleForTesting set

    fun provideNeoWsService(): NeoWsService {
        synchronized(lock) {
            return neoWsService ?: createNeoWsService()
        }
    }

    private fun createNeoWsService(): NeoWsService {
        return with(retrofitBuilder.build()) {
            return@with if (this.baseUrl().equals(NEO_BASE_API_URL))
                create(NeoWsService::class.java)
            else
                newBuilder().baseUrl(NEO_BASE_API_URL).build().create(NeoWsService::class.java)
        }
    }

    fun provideApodWsService(): ApodWsService {
        synchronized(lock) {
            return apodWsService ?: createApodWsService()
        }
    }

    private fun createApodWsService(): ApodWsService {
        return with(retrofitBuilder.build()) {
            return@with if (this.baseUrl().equals(APOD_BASE_API_URL))
                create(ApodWsService::class.java)
            else
                newBuilder().baseUrl(APOD_BASE_API_URL).build().create(ApodWsService::class.java)
        }
    }

    fun provideNearEarthObjectRepository(context: Context): AsteroidRepository {
        synchronized(lock) {
            return asteroidRepository ?: createNearEarthObjectRepository(context)
        }
    }

    private fun createNearEarthObjectRepository(context: Context): AsteroidRepository {
        val defaultRepository = DefaultAsteroidRepository(
            AsteroidRemoteDataSource,
            createNearEarthObjectLocalDataSource(context),
        )
        asteroidRepository = defaultRepository
        return defaultRepository
    }

    private fun createNearEarthObjectLocalDataSource(context: Context): AsteroidDataSource {
        val database = database ?: createDatabase(context)
        return AsteroidLocalDataSource(database.asteroidDao())
    }

    private fun createDatabase(context: Context): AsteroidDatabase {
        val databaseInstance = Room.databaseBuilder(
            context.applicationContext,
            AsteroidDatabase::class.java,
            DB_NAME,
        ).build()
        database = databaseInstance
        return databaseInstance
    }
}