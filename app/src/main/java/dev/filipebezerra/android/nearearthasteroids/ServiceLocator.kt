package dev.filipebezerra.android.nearearthasteroids

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.room.Room
import dev.filipebezerra.android.nearearthasteroids.data.source.remote.AsteroidRemoteDataSource
import dev.filipebezerra.android.nearearthasteroids.data.source.DefaultAsteroidRepository
import dev.filipebezerra.android.nearearthasteroids.data.source.AsteroidDataSource
import dev.filipebezerra.android.nearearthasteroids.data.source.AsteroidRepository
import dev.filipebezerra.android.nearearthasteroids.data.source.local.AsteroidDatabase
import dev.filipebezerra.android.nearearthasteroids.data.source.local.AsteroidDatabase.Companion.DB_NAME
import dev.filipebezerra.android.nearearthasteroids.data.source.local.AsteroidLocalDataSource

/**
 * A Service Locator object for the [AsteroidRepository].
 */
object ServiceLocator {

    private val lock = Any()

    private var database: AsteroidDatabase? = null

    @Volatile
    var asteroidRepository: AsteroidRepository? = null
        @VisibleForTesting set

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