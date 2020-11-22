package dev.filipebezerra.android.nearearthasteroids.ui.asteroiddetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.filipebezerra.android.nearearthasteroids.data.entity.Asteroid

class AsteroidDetailViewModel private constructor(
    private val asteroidId: String
) : ViewModel() {

    private val _asteroid = MutableLiveData<Asteroid>()
    val asteroid: LiveData<Asteroid>
        get() = _asteroid

    init {
        loadAsteroidDetail()
    }

    private fun loadAsteroidDetail() {
        _asteroid.value = null
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            asteroidId: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                AsteroidDetailViewModel(asteroidId) as T
        }
    }
}