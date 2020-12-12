package dev.filipebezerra.android.nearearthasteroids.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dev.filipebezerra.android.nearearthasteroids.work.RefreshAsteroidDataWork
import dev.filipebezerra.android.nearearthasteroids.work.RefreshAsteroidDataWork.Companion.REFRESH_ASTEROID_DATA_WORK
import timber.log.Timber

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
        private const val DB_NAME = "NearEarthObjects.db"

        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        fun createDatabase(context: Context): AsteroidDatabase = Room.databaseBuilder(
            context.applicationContext,
            AsteroidDatabase::class.java,
            DB_NAME,
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Timber.i("Database created. Scheduling one time work to refresh Asteroid data immediately")
                OneTimeWorkRequestBuilder<RefreshAsteroidDataWork>()
                    .addTag("OneTime-$REFRESH_ASTEROID_DATA_WORK")
                    .build()
                    .let { workRequest ->
                        WorkManager.getInstance(context.applicationContext).enqueue(workRequest)
                    }
            }
        }).build()
    }
}