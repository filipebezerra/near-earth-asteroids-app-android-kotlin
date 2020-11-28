package dev.filipebezerra.android.nearearthasteroids.ui.asteroidlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.filipebezerra.android.nearearthasteroids.data.entity.NearEarthObject
import dev.filipebezerra.android.nearearthasteroids.data.remote.ApiCallStatus
import dev.filipebezerra.android.nearearthasteroids.data.remote.NeoWsApi
import kotlinx.coroutines.launch

class AsteroidListViewModel : ViewModel() {

    private val _asteroids = MutableLiveData<List<NearEarthObject>>().apply {
        value = emptyList()
    }
    val asteroids: LiveData<List<NearEarthObject>>
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
                val neoFeed = NeoWsApi.service.retrieveNearEarthObjects()
                _asteroids.value = neoFeed.asteroidsByDate["2020-11-28"]
                _apiCallStatus.value = ApiCallStatus.SUCCESS
            } catch (fail: Exception) {
                _apiCallStatus.value = ApiCallStatus.ERROR
                // TODO: Log to Bugsnag/Firebase Crashlytics
                // TODO: Give visual feedback to user
            }
        }
    }
}