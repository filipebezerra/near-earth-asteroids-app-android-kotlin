package dev.filipebezerra.android.nearearthasteroids.ui.asteroidlist

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.filipebezerra.android.nearearthasteroids.data.entity.Asteroid

@BindingAdapter("asteroidList")
fun bindAsteroidList(listView: RecyclerView, asteroids: List<Asteroid>) =
    (listView.adapter as AsteroidListAdapter).submitList(asteroids)