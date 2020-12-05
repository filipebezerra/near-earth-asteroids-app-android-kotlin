package dev.filipebezerra.android.nearearthasteroids

import android.app.Application
import dev.filipebezerra.android.nearearthasteroids.data.source.AsteroidRepository
import timber.log.Timber

class NeaApplication : Application() {

    val asteroidRepository: AsteroidRepository
        get() = ServiceLocator.provideNearEarthObjectRepository(this)

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}