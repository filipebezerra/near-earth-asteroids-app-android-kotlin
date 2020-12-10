package dev.filipebezerra.android.nearearthasteroids.ui.asteroiddetail

import android.text.format.DateUtils.DAY_IN_MILLIS
import android.text.format.DateUtils.getRelativeTimeSpanString
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import dev.filipebezerra.android.nearearthasteroids.R
import dev.filipebezerra.android.nearearthasteroids.domain.CloseApproachData
import dev.filipebezerra.android.nearearthasteroids.domain.EstimatedDiameter
import dev.filipebezerra.android.nearearthasteroids.util.asIsoLocalDate
import dev.filipebezerra.android.nearearthasteroids.util.toEpochMilli

@BindingAdapter("imageUrl", "bitmap", "placeholder")
fun ImageView.bindImageUrl(
    imageUrl: String?,
    bitmap: Boolean? = false,
    placeholder: Int? = null,
) =
    imageUrl?.let {
        when (bitmap) {
            true -> Glide.with(context)
                .asBitmap()
                .load(imageUrl)
                .transition(BitmapTransitionOptions.withCrossFade())
            else -> Glide.with(context)
                .load(imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
        }.apply {
            placeholder?.let {
                placeholder(it)
                error(it)
            }
        }.run { into(this@bindImageUrl) }
    }

@BindingAdapter("asteroidHazardousOrSafe")
fun ImageView.bindAsteroidHazardousOrSafe(isPotentiallyHazardousAsteroid: Boolean) =
    Glide.with(context)
        .load(
            when (isPotentiallyHazardousAsteroid) {
                true -> R.drawable.asteroid_hazardous
                false -> R.drawable.asteroid_safe
            }
        )
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)

@BindingAdapter("closeApproachDate")
fun TextView.bindCloseApproachDate(closeApproachDataEntity: CloseApproachData?) =
    closeApproachDataEntity?.approachDate?.let { approachDate ->
        getRelativeTimeSpanString(
            System.currentTimeMillis(),
            approachDate.toEpochMilli(),
            DAY_IN_MILLIS
        ).let { relativeTime ->
            text = context.getString(
                R.string.close_approach_date_format,
                relativeTime,
                approachDate.asIsoLocalDate(),
            )
        }
    }

@BindingAdapter("estimatedDiameter")
fun TextView.bindEstimatedDiameter(estimatedDiameterEntity: EstimatedDiameter?) =
    estimatedDiameterEntity?.let {
        text = context.getString(
            R.string.estimated_diameter_format,
            it.minimumInMeters,
            it.maximumInMeters,
        )
    }

@BindingAdapter("relativeVelocity")
fun TextView.bindRelativeVelocity(closeApproachDataEntity: CloseApproachData?) =
    closeApproachDataEntity?.let {
        text = context.getString(
            R.string.relative_velocity_format,
            it.relativeVelocityKilometersPerSecond,
        )
    }

@BindingAdapter("distanceFromEarth")
fun TextView.bindDistanceFromEarth(closeApproachDataEntity: CloseApproachData?) =
    closeApproachDataEntity?.let {
        text = context.getString(
            R.string.distance_from_earth_format,
            it.missDistanceInKilometers,
        )
    }