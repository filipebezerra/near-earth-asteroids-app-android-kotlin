package dev.filipebezerra.android.nearearthasteroids.ui.asteroidlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.filipebezerra.android.nearearthasteroids.data.entity.NearEarthObject
import dev.filipebezerra.android.nearearthasteroids.databinding.AsteroidListItemBinding
import dev.filipebezerra.android.nearearthasteroids.ui.asteroidlist.AsteroidListScreenDirections.Companion.actionAsteroidListToAsteroidDetail as toAsteroidDetail

class AsteroidListAdapter : ListAdapter<NearEarthObject, AsteroidItemViewHolder>(AsteroidItemDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AsteroidItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: AsteroidItemViewHolder, position: Int) =
        holder.bindTo(getItem(position))
}

class AsteroidItemViewHolder private constructor(
    private val itemBinding: AsteroidListItemBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    init {
        itemBinding.setClickListener { view -> itemBinding.asteroid?.id?.let { asteroidId ->
            navigateToAsteroidDetail(view, asteroidId)
        }}
    }

    private fun navigateToAsteroidDetail(view: View, asteroidId: String) =
        view.findNavController().navigate(toAsteroidDetail(asteroidId))

    fun bindTo(item: NearEarthObject) = with(itemBinding) {
        asteroid = item
        executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): AsteroidItemViewHolder {
            val itemBinding = AsteroidListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return AsteroidItemViewHolder(itemBinding)
        }
    }
}

class AsteroidItemDiff : DiffUtil.ItemCallback<NearEarthObject>() {
    override fun areItemsTheSame(oldItem: NearEarthObject, newItem: NearEarthObject) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: NearEarthObject, newItem: NearEarthObject) =
        oldItem == newItem
}