package dev.filipebezerra.android.nearearthasteroids.ui.asteroidlist

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.filipebezerra.android.nearearthasteroids.data.entity.NearEarthObject

@BindingAdapter("asteroidList")
fun bindAsteroidList(listView: RecyclerView, nearEarthObjects: List<NearEarthObject>) =
    (listView.adapter as AsteroidListAdapter).submitList(nearEarthObjects)