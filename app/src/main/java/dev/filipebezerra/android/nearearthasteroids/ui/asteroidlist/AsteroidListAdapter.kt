package dev.filipebezerra.android.nearearthasteroids.ui.asteroidlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.filipebezerra.android.nearearthasteroids.data.entity.Asteroid
import dev.filipebezerra.android.nearearthasteroids.databinding.AsteroidListItemBinding

class AsteroidListAdapter : ListAdapter<Asteroid, AsteroidItemViewHolder>(AsteroidItemDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AsteroidItemViewHolder.from(parent)

    override fun onBindViewHolder(holder: AsteroidItemViewHolder, position: Int) =
        holder.bindTo(getItem(position))
}

class AsteroidItemViewHolder private constructor(
    private val itemBinding: AsteroidListItemBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bindTo(item: Asteroid) = with(itemBinding) {
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

class AsteroidItemDiff : DiffUtil.ItemCallback<Asteroid>() {
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid) =
        oldItem == newItem
}