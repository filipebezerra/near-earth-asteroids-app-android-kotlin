package dev.filipebezerra.android.nearearthasteroids.repository

import dev.filipebezerra.android.nearearthasteroids.database.AsteroidDao
import dev.filipebezerra.android.nearearthasteroids.database.asDomainModel
import dev.filipebezerra.android.nearearthasteroids.datasource.remote.NeoWsService
import dev.filipebezerra.android.nearearthasteroids.datasource.remote.asDomainModel
import dev.filipebezerra.android.nearearthasteroids.domain.Asteroid
import dev.filipebezerra.android.nearearthasteroids.domain.asEntity
import dev.filipebezerra.android.nearearthasteroids.util.LocalDateExt.dateNowAsIsoLocalDate
import dev.filipebezerra.android.nearearthasteroids.util.LocalDateExt.oneWeekFromNow
import dev.filipebezerra.android.nearearthasteroids.util.asIsoLocalDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

/**
 * Default implementation of [AsteroidRepository]. Single entry point for managing [Asteroid] data.
 */
class DefaultAsteroidRepository(
    private val asteroidDao: AsteroidDao,
    private val neoWsService: NeoWsService,
) : AsteroidRepository, BaseDefaultRepository() {

    override suspend fun refreshAsteroids() {
        logBeforeCall("refreshing Near Earth objects from NEO API")
            .let { startTimeGettingObjectsFromRemote ->
                neoWsService.retrieveNearEarthObjects(
                    dateNowAsIsoLocalDate(),
                    oneWeekFromNow().asIsoLocalDate()
                ).also {
                    logAfterCall(
                        "refreshing Near Earth objects from NEO API",
                        startTimeGettingObjectsFromRemote
                    )
                    saveAsteroids(it.asDomainModel())
                }
            }
    }

    override fun observeAsteroids(
        fromDate: LocalDate,
        toDate: LocalDate,
    ): Flow<List<Asteroid>> =
        asteroidDao.observeAsteroids(fromDate, toDate).map { it.asDomainModel() }

    override suspend fun saveAsteroids(asteroids: List<Asteroid>): List<Long> =
        logBeforeCall("saving Near Earth objects to local Data Source").let { startTime ->
            asteroidDao.insertAll(asteroids.asEntity()).also {
                logAfterCall("saving Near Earth objects to local Data Source", startTime)
            }
        }
}