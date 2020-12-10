package dev.filipebezerra.android.nearearthasteroids

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.filipebezerra.android.nearearthasteroids.database.AsteroidDao
import dev.filipebezerra.android.nearearthasteroids.database.AsteroidDatabase
import dev.filipebezerra.android.nearearthasteroids.database.AsteroidDatabase.Companion.DB_NAME
import dev.filipebezerra.android.nearearthasteroids.datasource.remote.ApodWsService
import dev.filipebezerra.android.nearearthasteroids.datasource.remote.NeoWsService
import dev.filipebezerra.android.nearearthasteroids.repository.AsteroidRepository
import dev.filipebezerra.android.nearearthasteroids.repository.DefaultAsteroidRepository
import dev.filipebezerra.android.nearearthasteroids.repository.DefaultPictureOfDayRepository
import dev.filipebezerra.android.nearearthasteroids.repository.PictureOfDayRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val NEO_BASE_API_URL = "https://api.nasa.gov/neo/rest/v1/"
private const val APOD_BASE_API_URL = "https://api.nasa.gov/planetary/"

// TODO: Migrate DI using Koin
/**
 * A Service Locator object for providing application dependencies.
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
    var pictureOfDayRepository: PictureOfDayRepository? = null
        @VisibleForTesting set

    @Volatile
    var neoWsService: NeoWsService? = null
        @VisibleForTesting set

    @Volatile
    var apodWsService: ApodWsService? = null
        @VisibleForTesting set

    fun provideAsteroidRepository(context: Context): AsteroidRepository = synchronized(lock) {
        asteroidRepository ?: createAsteroidRepository(context)
    }

    private fun createAsteroidRepository(context: Context): AsteroidRepository =
        DefaultAsteroidRepository(
            provideAsteroidDao(context),
            provideNeoWsService(),
        ).apply { asteroidRepository = this }

    private fun provideAsteroidDao(context: Context): AsteroidDao =
        createDatabase(context).run { asteroidDao() }

    private fun createDatabase(context: Context): AsteroidDatabase = Room.databaseBuilder(
        context.applicationContext,
        AsteroidDatabase::class.java,
        DB_NAME,
    ).build().apply { database = this }

    private fun provideNeoWsService(): NeoWsService = synchronized(lock) {
        neoWsService ?: createNeoWsService()
    }

    private fun createNeoWsService(): NeoWsService = with(retrofitBuilder.build()) {
        if (this.baseUrl().equals(NEO_BASE_API_URL))
            create(NeoWsService::class.java)
        else
            newBuilder().baseUrl(NEO_BASE_API_URL).build().create(NeoWsService::class.java)
    }

    fun providePictureOfTheDayRepository(): PictureOfDayRepository = synchronized(lock) {
        pictureOfDayRepository ?: createPictureOfDayRepository()
    }

    private fun createPictureOfDayRepository(): PictureOfDayRepository =
        DefaultPictureOfDayRepository(provideApodWsService())
            .apply { pictureOfDayRepository = this }

    private fun provideApodWsService(): ApodWsService = synchronized(lock) {
        apodWsService ?: createApodWsService()
    }

    private fun createApodWsService(): ApodWsService = with(retrofitBuilder.build()) {
        if (this.baseUrl().equals(APOD_BASE_API_URL))
            create(ApodWsService::class.java)
        else
            newBuilder().baseUrl(APOD_BASE_API_URL).build().create(ApodWsService::class.java)
    }
}