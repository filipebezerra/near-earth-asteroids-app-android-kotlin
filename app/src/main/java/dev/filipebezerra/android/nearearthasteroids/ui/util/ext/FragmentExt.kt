package dev.filipebezerra.android.nearearthasteroids.ui.util.ext

import androidx.fragment.app.Fragment
import dev.filipebezerra.android.nearearthasteroids.NeaApplication
import dev.filipebezerra.android.nearearthasteroids.ViewModelFactory

fun Fragment.getViewModelFactory(): ViewModelFactory =
    with((requireContext().applicationContext as NeaApplication)) {
        ViewModelFactory(
            asteroidRepository,
            pictureOfDayRepository,
        this@getViewModelFactory,
        )
    }