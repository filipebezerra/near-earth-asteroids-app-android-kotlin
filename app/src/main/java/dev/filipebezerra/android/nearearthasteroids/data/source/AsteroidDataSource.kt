package dev.filipebezerra.android.nearearthasteroids.data.source

import dev.filipebezerra.android.nearearthasteroids.data.entity.Asteroid
import kotlinx.coroutines.flow.Flow

/**
 * Main entry point for accessing [Asteroid] data.
 */
interface AsteroidDataSource {

    suspend fun getAsteroids(): List<Asteroid>

    fun observeAsteroids(): Flow<List<Asteroid>>

    suspend fun saveAsteroids(asteroids: List<Asteroid>): List<Long>
}