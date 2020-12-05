package dev.filipebezerra.android.nearearthasteroids.data.source.local

import androidx.room.*
import dev.filipebezerra.android.nearearthasteroids.data.entity.Asteroid
import kotlinx.coroutines.flow.Flow

@Dao
interface AsteroidDao {

    /**
     * Insert a list of [Asteroid] in the database. If the [Asteroid] already exists, replace it.
     *
     * @param asteroids the list of [Asteroid] to be inserted
     * @return the list of new `rowId` for each [Asteroid] inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(asteroids: List<Asteroid>): List<Long>

    /**
     * Query and observe list of [Asteroid].
     *
     * @return all [Asteroid].
     */
    @Query("SELECT * FROM asteroids AS ast ORDER BY ast.close_approach_data_date DESC")
    fun listAsteroids(): Flow<List<Asteroid>>

    /**
     * Query synchronously a list of [Asteroid].
     *
     * @return all [Asteroid].
     */
    @Transaction
    @Query("SELECT * FROM asteroids AS ast ORDER BY ast.close_approach_data_date DESC")
    suspend fun listAsteroidsSync(): List<Asteroid>
}