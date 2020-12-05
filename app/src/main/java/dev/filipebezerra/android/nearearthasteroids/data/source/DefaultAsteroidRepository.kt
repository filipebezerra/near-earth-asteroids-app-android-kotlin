package dev.filipebezerra.android.nearearthasteroids.data.source

import dev.filipebezerra.android.nearearthasteroids.data.entity.Asteroid
import kotlinx.coroutines.flow.Flow
import timber.log.Timber
import java.time.LocalTime
import java.time.temporal.ChronoUnit

/**
 * Default implementation of [AsteroidRepository]. Single entry point for managing [Asteroid] data.
 */
class DefaultAsteroidRepository(
    private val asteroidRemoteDataSource: AsteroidDataSource,
    private val asteroidLocalDataSource: AsteroidDataSource,
) : AsteroidRepository {

    override suspend fun getAsteroids(): List<Asteroid> {
        return logBeforeCall("getting Near Earth objects from NEO API")
            .let { startTimeGettingObjectsFromRemote ->
                asteroidRemoteDataSource.getAsteroids()
                    .also {
                        logAfterCall(
                            "getting Near Earth objects from NEO API",
                            startTimeGettingObjectsFromRemote
                        )
                        saveAsteroids(it)
                    }
                    .run {
                        logBeforeCall("getting Near Earth objects from local Data Source")
                    }
                    .let { startTimeGettingObjectsFromLocal ->
                        asteroidLocalDataSource.getAsteroids().also {
                            logAfterCall(
                                "getting Near Earth objects from local Data Source",
                                startTimeGettingObjectsFromLocal
                            )
                        }
                    }
            }
    }

    override fun observeAsteroids(): Flow<List<Asteroid>> {
        return logBeforeCall("observing Near Earth objects from local Data Source").let { startTime ->
            asteroidLocalDataSource.observeAsteroids()
                .also {
                    logAfterCall("observing Near Earth objects from local Data Source", startTime)
                }
        }
    }

    override suspend fun saveAsteroids(asteroids: List<Asteroid>): List<Long> {
        return logBeforeCall("saving Near Earth objects to local Data Source").let { startTime ->
            asteroidLocalDataSource.saveAsteroids(asteroids)
                .also {
                    logAfterCall("saving Near Earth objects to local Data Source", startTime)
                }
        }
    }

    private fun logBeforeCall(callLogDescription: String): LocalTime {
        return Timber.i("Data layer: starting $callLogDescription")
            .run { LocalTime.now() }
    }

    private fun logAfterCall(callLogDescription: String, startTime: LocalTime) {
        LocalTime.now().let { finishTime ->
            ChronoUnit.SECONDS.between(startTime, finishTime).apply {
                takeIf { it > 0L }?.let { timeBetweenInSeconds ->
                    Timber.i("Data layer: Successfully done $callLogDescription in $timeBetweenInSeconds s")
                }
                takeUnless { it > 0L }?.let {
                    ChronoUnit.MILLIS.between(startTime, finishTime).let { timeBetweenInMillis ->
                        Timber.i("Data layer: Successfully done $callLogDescription in $timeBetweenInMillis ms")
                    }
                }
            }
        }
    }
}