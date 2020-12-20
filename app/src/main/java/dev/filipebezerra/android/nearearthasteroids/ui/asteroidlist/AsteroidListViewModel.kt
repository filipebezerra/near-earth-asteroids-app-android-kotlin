package dev.filipebezerra.android.nearearthasteroids.ui.asteroidlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.work.WorkInfo
import androidx.work.WorkManager
import dev.filipebezerra.android.nearearthasteroids.domain.Asteroid
import dev.filipebezerra.android.nearearthasteroids.domain.PictureOfDay
import dev.filipebezerra.android.nearearthasteroids.repository.AsteroidRepository
import dev.filipebezerra.android.nearearthasteroids.repository.PictureOfDayRepository
import dev.filipebezerra.android.nearearthasteroids.util.ext.LocalDateExt.oneWeekFromNow
import dev.filipebezerra.android.nearearthasteroids.work.RefreshAsteroidDataWork.Companion.GET_INITIAL_ASTEROID_DATA_WORK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import java.time.LocalDate.now

class AsteroidListViewModel(
    asteroidRepository: AsteroidRepository,
    pictureOfDayRepository: PictureOfDayRepository,
    workManager: WorkManager,
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
            // TODO: Give visual feedback to user when fail loading asteroids
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
            // TODO: Give visual feedback to user when fail loading picture of day
        }
        .asLiveData()

    val initialAsteroidDataState =
        Transformations.map(workManager.getWorkInfosByTagLiveData(GET_INITIAL_ASTEROID_DATA_WORK)) {
            it.takeUnless { it.isEmpty() }?.first()?.state ?: WorkInfo.State.SUCCEEDED
        }
}