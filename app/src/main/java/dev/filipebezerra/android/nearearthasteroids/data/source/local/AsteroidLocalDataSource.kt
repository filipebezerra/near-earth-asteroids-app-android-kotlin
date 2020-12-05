package dev.filipebezerra.android.nearearthasteroids.data.source.local

import dev.filipebezerra.android.nearearthasteroids.data.entity.Asteroid
import dev.filipebezerra.android.nearearthasteroids.data.source.AsteroidDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Concrete implementation of data source of [AsteroidDataSource] as a local Database storage.
 */
class AsteroidLocalDataSource internal constructor(
    private val asteroidDao: AsteroidDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : AsteroidDataSource {

    override suspend fun getAsteroids(): List<Asteroid> = withContext(ioDispatcher) {
        return@withContext asteroidDao.listAsteroidsSync()
    }

    override fun observeAsteroids() = asteroidDao.listAsteroids()

    override suspend fun saveAsteroids(asteroids: List<Asteroid>) =
        asteroidDao.insertAll(asteroids)
}