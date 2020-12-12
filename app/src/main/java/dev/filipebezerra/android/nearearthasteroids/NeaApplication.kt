package dev.filipebezerra.android.nearearthasteroids

import android.annotation.SuppressLint
import android.app.Application
import androidx.work.*
import dev.filipebezerra.android.nearearthasteroids.repository.AsteroidRepository
import dev.filipebezerra.android.nearearthasteroids.repository.PictureOfDayRepository
import dev.filipebezerra.android.nearearthasteroids.work.RefreshAsteroidDataWork
import dev.filipebezerra.android.nearearthasteroids.work.RefreshAsteroidDataWork.Companion.REFRESH_ASTEROID_DATA_WORK
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit.DAYS

class NeaApplication : Application(), Configuration.Provider {

    val asteroidRepository: AsteroidRepository
        get() = ServiceLocator.provideAsteroidRepository(this)

    val pictureOfDayRepository: PictureOfDayRepository
        get() = ServiceLocator.providePictureOfTheDayRepository()

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun getWorkManagerConfiguration(): Configuration {
        return if (BuildConfig.DEBUG) {
            Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.DEBUG)
                .build()
        } else {
            Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.ERROR)
                .build()
        }
    }

    override fun onCreate() {
        super.onCreate()
        BuildConfig.DEBUG.takeIf { it }?.let { Timber.plant(Timber.DebugTree()) }
        delayedInit()
    }

    private fun delayedInit() = applicationScope.launch {
        setupRefreshAsteroidDataWork()
    }

    @SuppressLint("NewApi")
    private fun setupRefreshAsteroidDataWork() {
        Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .apply {
                android.os.Build.VERSION.SDK_INT
                    .takeIf { it >= android.os.Build.VERSION_CODES.M }
                    ?.apply { setRequiresDeviceIdle(true) }
            }
            .build()
            .let { constraints ->
                PeriodicWorkRequestBuilder<RefreshAsteroidDataWork>(1, DAYS)
                    .setConstraints(constraints)
                    .addTag("Periodic-$REFRESH_ASTEROID_DATA_WORK")
                    .build()
            }
            .let { workRequest ->
                // https://developer.android.com/topic/libraries/architecture/workmanager/how-to/define-work#schedule_periodic_work
                WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                    REFRESH_ASTEROID_DATA_WORK,
                    ExistingPeriodicWorkPolicy.KEEP,
                    workRequest
                )
            }
    }
}