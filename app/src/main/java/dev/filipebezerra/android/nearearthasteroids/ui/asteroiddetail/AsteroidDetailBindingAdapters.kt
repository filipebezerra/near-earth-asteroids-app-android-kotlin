package dev.filipebezerra.android.nearearthasteroids.ui.asteroiddetail

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import dev.filipebezerra.android.nearearthasteroids.R

@BindingAdapter("asteroidHazardousOrSafe")
fun bindAsteroidHazardousOrSafe(view: ImageView, isPotentiallyHazardousAsteroid: Boolean) {
    view.setImageResource(
        when(isPotentiallyHazardousAsteroid) {
            true -> R.drawable.asteroid_hazardous
            false -> R.drawable.asteroid_safe
        }
    )
}