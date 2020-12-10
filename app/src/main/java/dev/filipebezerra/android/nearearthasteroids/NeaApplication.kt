package dev.filipebezerra.android.nearearthasteroids

import android.app.Application
import dev.filipebezerra.android.nearearthasteroids.repository.AsteroidRepository
import dev.filipebezerra.android.nearearthasteroids.repository.PictureOfDayRepository
import timber.log.Timber

class NeaApplication : Application() {

    val asteroidRepository: AsteroidRepository
        get() = ServiceLocator.provideAsteroidRepository(this)

    val pictureOfDayRepository: PictureOfDayRepository
        get() = ServiceLocator.providePictureOfTheDayRepository()

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}