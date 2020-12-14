package dev.filipebezerra.android.nearearthasteroids.ui.asteroidlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dev.filipebezerra.android.nearearthasteroids.domain.Asteroid
import dev.filipebezerra.android.nearearthasteroids.domain.PictureOfDay
import dev.filipebezerra.android.nearearthasteroids.repository.AsteroidRepository
import dev.filipebezerra.android.nearearthasteroids.repository.PictureOfDayRepository
import dev.filipebezerra.android.nearearthasteroids.util.LocalDateExt.oneWeekFromNow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import java.time.LocalDate.now

class AsteroidListViewModel(
    asteroidRepository: AsteroidRepository,
    pictureOfDayRepository: PictureOfDayRepository,
) : ViewModel() {

    @ExperimentalCoroutinesApi
    val asteroids: LiveData<List<Asteroid>> = asteroidRepository.observeAsteroids(
        now(),
        oneWeekFromNow(),
    )
        .onStart {
            // TODO: Give visual feedback to user when start loading asteroids
        }
        .onCompletion { cause ->
            if (cause == null) {
            // TODO: Give visual feedback to user when complete loading asteroids
            }
        }
        .catch { error ->
            // TODO: Log to Bugsnag/Firebase Crashlytics
            // TODO: Give visual feedback to user
        }
        .asLiveData()

    @ExperimentalCoroutinesApi
    val pictureOfDay: LiveData<PictureOfDay> = pictureOfDayRepository.getPictureOfTheDay()
        .onStart {
            // TODO: Give visual feedback to user when start loading picture of day
        }
        .onCompletion { cause ->
            if (cause == null) {
            // TODO: Give visual feedback to user when complete loading picture of day
            }
        }
        .catch { error ->
            // TODO: Log to Bugsnag/Firebase Crashlytics
            // TODO: Give visual feedback to user
        }
        .asLiveData()

    fun retryLoadAsteroidList() {}
}