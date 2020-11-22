package dev.filipebezerra.android.nearearthasteroids.ui.asteroidlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.filipebezerra.android.nearearthasteroids.data.entity.*

class AsteroidListViewModel : ViewModel() {

    private val _asteroids = MutableLiveData<List<Asteroid>>()
    val asteroids: LiveData<List<Asteroid>>
        get() = _asteroids

    init {
        loadAsteroidList()
    }

    private fun loadAsteroidList() {
        _asteroids.value = listOf(
            Asteroid(
                id = "2007753",
                name = "7753 (1988 XB)",
                nasaJplUrl = "http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=2007753",
                absoluteMagnitude = 18.6F,
                estimatedDiameter = EstimatedDiameter(
                    EstimatedDiameterInKilometers(
                        maximum = 1.1325046106,
                    ),
                ),
                isPotentiallyHazardousAsteroid = true,
                closeApproachData = listOf(
                    CloseApproachData(
                        relativeVelocity = RelativeVelocity(
                            kilometersPerSecond = 11.5154455613,
                        ),
                        missDistance = MissDistance(
                            astronomical = 0.066192904,
                        ),
                    ),
                ),
                orbitalData = OrbitalData(
                    orbitClass = OrbitClass(
                        description = "Near-Earth asteroid orbits which cross the Earth’s orbit similar to that of 1862 Apollo"
                    )
                ),
            ),

            Asteroid(
                id = "2007753",
                name = "7753 (1988 XB)",
                nasaJplUrl = "http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=2007753",
                absoluteMagnitude = 18.6F,
                estimatedDiameter = EstimatedDiameter(
                    EstimatedDiameterInKilometers(
                        maximum = 1.1325046106,
                    ),
                ),
                isPotentiallyHazardousAsteroid = true,
                closeApproachData = listOf(
                    CloseApproachData(
                        relativeVelocity = RelativeVelocity(
                            kilometersPerSecond = 11.5154455613,
                        ),
                        missDistance = MissDistance(
                            astronomical = 0.066192904,
                        ),
                    ),
                ),
                orbitalData = OrbitalData(
                    orbitClass = OrbitClass(
                        description = "Near-Earth asteroid orbits which cross the Earth’s orbit similar to that of 1862 Apollo"
                    )
                ),
            ),

            Asteroid(
                id = "2007753",
                name = "7753 (1988 XB)",
                nasaJplUrl = "http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=2007753",
                absoluteMagnitude = 18.6F,
                estimatedDiameter = EstimatedDiameter(
                    EstimatedDiameterInKilometers(
                        maximum = 1.1325046106,
                    ),
                ),
                isPotentiallyHazardousAsteroid = true,
                closeApproachData = listOf(
                    CloseApproachData(
                        relativeVelocity = RelativeVelocity(
                            kilometersPerSecond = 11.5154455613,
                        ),
                        missDistance = MissDistance(
                            astronomical = 0.066192904,
                        ),
                    ),
                ),
                orbitalData = OrbitalData(
                    orbitClass = OrbitClass(
                        description = "Near-Earth asteroid orbits which cross the Earth’s orbit similar to that of 1862 Apollo"
                    )
                ),
            ),

            Asteroid(
                id = "2007753",
                name = "7753 (1988 XB)",
                nasaJplUrl = "http://ssd.jpl.nasa.gov/sbdb.cgi?sstr=2007753",
                absoluteMagnitude = 18.6F,
                estimatedDiameter = EstimatedDiameter(
                    EstimatedDiameterInKilometers(
                        maximum = 1.1325046106,
                    ),
                ),
                isPotentiallyHazardousAsteroid = true,
                closeApproachData = listOf(
                    CloseApproachData(
                        relativeVelocity = RelativeVelocity(
                            kilometersPerSecond = 11.5154455613,
                        ),
                        missDistance = MissDistance(
                            astronomical = 0.066192904,
                        ),
                    ),
                ),
                orbitalData = OrbitalData(
                    orbitClass = OrbitClass(
                        description = "Near-Earth asteroid orbits which cross the Earth’s orbit similar to that of 1862 Apollo"
                    )
                ),
            ),
        )
    }
}