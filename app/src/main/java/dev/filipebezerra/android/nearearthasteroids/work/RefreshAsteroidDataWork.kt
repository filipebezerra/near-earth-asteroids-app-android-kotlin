package dev.filipebezerra.android.nearearthasteroids.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dev.filipebezerra.android.nearearthasteroids.NeaApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

class RefreshAsteroidDataWork(
    appContext: Context,
    params: WorkerParameters,
) :
    CoroutineWorker(
        appContext,
        params,
    ) {

    private val workId = tags.joinToString(
        prefix = "tags={ ",
        postfix = " }"
    ).let { tags -> "[ id=$id, $tags ]" }

    override suspend fun doWork(): Result =
        withContext(Dispatchers.IO) {
            try {
                (applicationContext as NeaApplication).asteroidRepository.run {
                    Timber.i("Starting refresh Asteroid work $workId")
                    refreshAsteroids()
                    Timber.i("Refresh Asteroid work done successfully $workId")
                    Result.success()
                }
            } catch (error: RuntimeException) {
                Timber.e(
                    error,
                    "Failed to complete refresh Asteroid work. Signalling to retry  $workId"
                )
                // TODO Log to Crashlytics/Bugsnag
                Result.retry()
            }
        }

    companion object {
        const val REFRESH_ASTEROID_DATA_WORK = "RefreshAsteroidDataWork"
    }
}