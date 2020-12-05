package dev.filipebezerra.android.nearearthasteroids.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.filipebezerra.android.nearearthasteroids.data.entity.Asteroid
import dev.filipebezerra.android.nearearthasteroids.data.entity.CloseApproachData

/**
 * The Room database for this app that contains the [Asteroid]s.
 */
@Database(
    entities = [
        Asteroid::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(Converters::class)
abstract class AsteroidDatabase : RoomDatabase() {

    /**
     * The [Asteroid] Data Access Object.
     */
    abstract fun asteroidDao(): AsteroidDao

    companion object {
        const val DB_NAME = "NearEarthObjects.db"
    }
}