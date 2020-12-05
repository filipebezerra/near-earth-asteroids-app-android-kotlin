package dev.filipebezerra.android.nearearthasteroids.ui.asteroiddetail

import android.text.format.DateUtils.DAY_IN_MILLIS
import android.text.format.DateUtils.getRelativeTimeSpanString
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import dev.filipebezerra.android.nearearthasteroids.R
import dev.filipebezerra.android.nearearthasteroids.data.entity.CloseApproachData
import dev.filipebezerra.android.nearearthasteroids.data.entity.EstimatedDiameter
import dev.filipebezerra.android.nearearthasteroids.util.toEpochMilli
import dev.filipebezerra.android.nearearthasteroids.util.toIsoLocalDate

@BindingAdapter("asteroidHazardousOrSafe")
fun ImageView.bindAsteroidHazardousOrSafe(isPotentiallyHazardousAsteroid: Boolean) =
    setImageResource(
        when (isPotentiallyHazardousAsteroid) {
            true -> R.drawable.asteroid_hazardous
            false -> R.drawable.asteroid_safe
        }
    )

@BindingAdapter("closeApproachDate")
fun TextView.bindCloseApproachDate(closeApproachData: CloseApproachData?) =
    closeApproachData?.approachDate?.let { approachDate ->
        getRelativeTimeSpanString(
            System.currentTimeMillis(),
            approachDate.toEpochMilli(),
            DAY_IN_MILLIS
        ).let { relativeTime ->
            text = context.getString(
                R.string.close_approach_date_format,
                relativeTime,
                approachDate.toIsoLocalDate(),
            )
        }
    }

@BindingAdapter("estimatedDiameter")
fun TextView.bindEstimatedDiameter(estimatedDiameter: EstimatedDiameter?) = estimatedDiameter?.let {
    text = context.getString(
        R.string.estimated_diameter_format,
        it.minimumInMeters,
        it.maximumInMeters,
    )
}

@BindingAdapter("relativeVelocity")
fun TextView.bindRelativeVelocity(closeApproachData: CloseApproachData?) = closeApproachData?.let {
    text = context.getString(
        R.string.relative_velocity_format,
        it.relativeVelocityKilometersPerSecond,
    )
}

@BindingAdapter("distanceFromEarth")
fun TextView.bindDistanceFromEarth(closeApproachData: CloseApproachData?) = closeApproachData?.let {
    text = context.getString(
        R.string.distance_from_earth_format,
        it.missDistanceInKilometers,
    )
}