package dev.filipebezerra.android.nearearthasteroids.ui.asteroidlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.filipebezerra.android.nearearthasteroids.data.entity.NearEarthObject
import dev.filipebezerra.android.nearearthasteroids.data.remote.NeoWsApi
import kotlinx.coroutines.launch

class AsteroidListViewModel : ViewModel() {

    private val _asteroids = MutableLiveData<List<NearEarthObject>>().apply {
        value = emptyList()
    }
    val asteroids: LiveData<List<NearEarthObject>>
        get() = _asteroids

    init {
        loadAsteroidList()
    }

    private fun loadAsteroidList() {
        viewModelScope.launch {
            try {
                val neoFeed = NeoWsApi.service.retrieveNearEarthObjects()
                _asteroids.value = neoFeed.asteroidsByDate["2020-11-26"]
            } catch (fail: Exception) {
                // TODO: Log to Bugsnag/Firebase Crashlytics
                // TODO: Give visual feedback to user
            }
        }
    }
}