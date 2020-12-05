package dev.filipebezerra.android.nearearthasteroids.data.source

import dev.filipebezerra.android.nearearthasteroids.data.entity.Asteroid
import kotlinx.coroutines.flow.Flow

/**
 * Interface to the data layer.
 */
interface AsteroidRepository {

    suspend fun getAsteroids(): List<Asteroid>

    fun observeAsteroids(): Flow<List<Asteroid>>

    suspend fun saveAsteroids(asteroids: List<Asteroid>): List<Long>
}