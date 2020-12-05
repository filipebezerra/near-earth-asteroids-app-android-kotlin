package dev.filipebezerra.android.nearearthasteroids.ui.asteroiddetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.filipebezerra.android.nearearthasteroids.data.entity.Asteroid

class AsteroidDetailViewModel : ViewModel() {

    private val _asteroid = MutableLiveData<Asteroid>()
    val asteroid: LiveData<Asteroid>
        get() = _asteroid

    fun initialize(asteroid: Asteroid) {
        _asteroid.value = asteroid
    }
}