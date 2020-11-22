package dev.filipebezerra.android.nearearthasteroids.ui.asteroiddetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.filipebezerra.android.nearearthasteroids.R
import dev.filipebezerra.android.nearearthasteroids.databinding.AsteroidDetailScreenBinding

class AsteroidDetailScreen : Fragment() {

    private val asteroidDetailViewModel: AsteroidDetailViewModel by viewModels()

    private lateinit var viewBinding: AsteroidDetailScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = AsteroidDetailScreenBinding.inflate(inflater)
        .apply {
            viewBinding = this
            viewModel = asteroidDetailViewModel
            lifecycleOwner = viewLifecycleOwner
        }
        .root
}