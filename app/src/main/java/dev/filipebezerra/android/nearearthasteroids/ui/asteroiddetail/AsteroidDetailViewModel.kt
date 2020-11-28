package dev.filipebezerra.android.nearearthasteroids.ui.asteroiddetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.filipebezerra.android.nearearthasteroids.data.entity.NearEarthObject

class AsteroidDetailViewModel private constructor(
    private val asteroid: NearEarthObject
) : ViewModel() {

    private val _asteroid = MutableLiveData<NearEarthObject>().apply { value = asteroid }
    val nearEarthObject: LiveData<NearEarthObject>
        get() = _asteroid

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            asteroidId: NearEarthObject
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                AsteroidDetailViewModel(asteroidId) as T
        }
    }
}