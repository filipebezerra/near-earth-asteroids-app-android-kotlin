package dev.filipebezerra.android.nearearthasteroids.data.entity

import android.os.Parcelable
import androidx.room.*
import dev.filipebezerra.android.nearearthasteroids.data.NearEarthObject
import dev.filipebezerra.android.nearearthasteroids.util.toLocalDate
import kotlinx.parcelize.Parcelize

@Entity(
    tableName = "asteroids",
    indices = [
        Index(value = ["neo_id"], unique = true),
        Index(value = ["name"]),
    ],
)
@Parcelize
data class Asteroid(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "neo_id") val neoId: String? = null,
    @ColumnInfo(name = "name") val name: String? = null,
    @ColumnInfo(name = "nasa_jpl_url") val nasaJplUrl: String? = null,
    @ColumnInfo(name = "absolute_magnitude") val absoluteMagnitude: Float? = null,
    @Embedded(prefix = "estimated_diameter_") val estimatedDiameter: EstimatedDiameter? = null,
    @ColumnInfo(name = "is_potentially_hazardous") val isPotentiallyHazardousAsteroid: Boolean? = null,
    @Embedded(prefix = "close_approach_data_") val closeApproachData: CloseApproachData? = null,
    @ColumnInfo(name = "orbit_class_description") val orbitClassDescription: String? = null,
) : Parcelable {

    constructor(nearEarthObject: NearEarthObject) : this(
        neoId = nearEarthObject.id,
        name = nearEarthObject.name,
        nasaJplUrl = nearEarthObject.nasaJplUrl,
        absoluteMagnitude = nearEarthObject.absoluteMagnitude,
        estimatedDiameter = EstimatedDiameter(
            minimumInMeters = nearEarthObject.estimatedDiameter?.meters?.minimumDiameter,
            maximumInMeters = nearEarthObject.estimatedDiameter?.meters?.maximumDiameter,
        ),
        isPotentiallyHazardousAsteroid = nearEarthObject.isPotentiallyHazardousAsteroid,
        closeApproachData = nearEarthObject.closeApproachData?.firstOrNull()?.run {
            CloseApproachData(
                approachDate = approachDate?.toLocalDate(),
                relativeVelocityKilometersPerSecond = relativeVelocity?.kilometersPerSecond,
                missDistanceInKilometers = missDistance?.kilometers
            )
        },
        orbitClassDescription = nearEarthObject.orbitalData?.orbitClass?.description
    )
}