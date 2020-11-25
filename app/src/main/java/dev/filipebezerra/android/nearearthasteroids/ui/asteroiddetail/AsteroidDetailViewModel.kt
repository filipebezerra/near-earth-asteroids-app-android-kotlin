package dev.filipebezerra.android.nearearthasteroids.ui.asteroiddetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.filipebezerra.android.nearearthasteroids.data.entity.*

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
        _asteroid.value = Asteroid(
            id = "2007753",
            name = "7753 (1988 XB)",
            nasaJplUrl = "http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=2007753",
            absoluteMagnitude = 18.6F,
            estimatedDiameter = EstimatedDiameter(
                EstimatedDiameterInMeters(
                    minimum = 0.3195618867,
                    maximum = 0.7145621017,
                ),
            ),
            isPotentiallyHazardousAsteroid = true,
            closeApproachData = listOf(
                CloseApproachData(
                    approachDate = "2020-11-25",
                    approachDateFull = "2020-Nov-25 03:24",
                    relativeVelocity = RelativeVelocity(
                        kilometersPerSecond = 11.5154455613,
                    ),
                    missDistance = MissDistance(
                        kilometers = 0.066192904,
                    ),
                ),
            ),
            orbitalData = OrbitalData(
                orbitClass = OrbitClass(
                    description = "Near-Earth asteroid orbits which cross the Earth’s orbit similar to that of 1862 Apollo"
                )
            ),
        )
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