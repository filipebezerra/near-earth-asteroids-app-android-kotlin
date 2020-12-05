package dev.filipebezerra.android.nearearthasteroids.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.parcelize.Parcelize

@Parcelize
data class EstimatedDiameter(
    @ColumnInfo(name = "min_in_meters") val minimumInMeters: Double? = null,
    @ColumnInfo(name = "max_in_meters") val maximumInMeters: Double? = null,
) : Parcelable