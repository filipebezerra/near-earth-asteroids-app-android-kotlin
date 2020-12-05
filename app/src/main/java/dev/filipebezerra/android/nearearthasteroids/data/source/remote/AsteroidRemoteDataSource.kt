package dev.filipebezerra.android.nearearthasteroids.data.source.remote

import dev.filipebezerra.android.nearearthasteroids.data.entity.Asteroid
import dev.filipebezerra.android.nearearthasteroids.data.source.AsteroidDataSource
import dev.filipebezerra.android.nearearthasteroids.util.LocalDateExt.dateNowFormatted
import kotlinx.coroutines.flow.Flow

object AsteroidRemoteDataSource : AsteroidDataSource {

    override suspend fun getAsteroids(): List<Asteroid> =
        NeoWsApi.service.retrieveNearEarthObjects().asteroidsByDate
            .getOrDefault(dateNowFormatted(), emptyList()).map { Asteroid(it) }

    override fun observeAsteroids(): Flow<List<Asteroid>> {
        TODO("Not implemented")
    }

    override suspend fun saveAsteroids(asteroids: List<Asteroid>): List<Long> {
        TODO("Not implemented")
    }
}