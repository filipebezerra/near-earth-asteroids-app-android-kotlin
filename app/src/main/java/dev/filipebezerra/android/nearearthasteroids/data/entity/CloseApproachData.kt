package dev.filipebezerra.android.nearearthasteroids.data.entity

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize
import java.time.LocalDate

@Parcelize
data class CloseApproachData (
    @ColumnInfo(name = "date") val approachDate: LocalDate? = null,
    @ColumnInfo(name = "relative_velocity_kps") val relativeVelocityKilometersPerSecond: Double? = null,
    @ColumnInfo(name = "miss_distance_km") val missDistanceInKilometers: Double? = null,
) : Parcelable