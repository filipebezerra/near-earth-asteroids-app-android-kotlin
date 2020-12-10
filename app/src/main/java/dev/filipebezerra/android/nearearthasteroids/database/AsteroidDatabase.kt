package dev.filipebezerra.android.nearearthasteroids.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * The Room database for this app that contains the [AsteroidEntity]s.
 */
@Database(
    entities = [
        AsteroidEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(DatabaseConverters::class)
abstract class AsteroidDatabase : RoomDatabase() {

    /**
     * The [AsteroidEntity] Data Access Object.
     */
    abstract fun asteroidDao(): AsteroidDao

    companion object {
        const val DB_NAME = "NearEarthObjects.db"
    }
}