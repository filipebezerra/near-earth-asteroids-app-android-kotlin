package dev.filipebezerra.android.nearearthasteroids.ui.asteroidlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.filipebezerra.android.nearearthasteroids.data.entity.Asteroid
import dev.filipebezerra.android.nearearthasteroids.data.source.remote.ApiCallStatus
import dev.filipebezerra.android.nearearthasteroids.data.source.AsteroidRepository
import kotlinx.coroutines.launch

class AsteroidListViewModel(
    private val asteroidRepository: AsteroidRepository
) : ViewModel() {

    private val _asteroids = MutableLiveData<List<Asteroid>>().apply {
        value = emptyList()
    }
    val asteroids: LiveData<List<Asteroid>>
        get() = _asteroids

    private val _apiCallStatus = MutableLiveData<ApiCallStatus>()
    val apiCallStatus: LiveData<ApiCallStatus>
        get() = _apiCallStatus

    init {
        loadAsteroidList()
    }

    fun retryLoadAsteroidList() = loadAsteroidList()

    private fun loadAsteroidList() {
        viewModelScope.launch {
            try {
                _apiCallStatus.value = ApiCallStatus.LOADING
                _asteroids.value = asteroidRepository.getAsteroids()
                _apiCallStatus.value = ApiCallStatus.SUCCESS
            } catch (fail: Exception) {
                _apiCallStatus.value = ApiCallStatus.ERROR
                // TODO: Log to Bugsnag/Firebase Crashlytics
                // TODO: Give visual feedback to user
            }
        }
    }
}