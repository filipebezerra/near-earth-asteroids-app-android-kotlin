package dev.filipebezerra.android.nearearthasteroids.ui.asteroidlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dev.filipebezerra.android.nearearthasteroids.databinding.AsteroidListScreenBinding

class AsteroidListScreen : Fragment() {

    private val asteroidListViewModel: AsteroidListViewModel by viewModels()

    private lateinit var viewBinding: AsteroidListScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = AsteroidListScreenBinding.inflate(inflater)
        .apply {
            viewBinding = this
            viewModel = asteroidListViewModel
        }
        .root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createAndroidListAdapter()
        viewBinding.lifecycleOwner = viewLifecycleOwner
    }

    private fun createAndroidListAdapter() {
        viewBinding.asteroidList.adapter = AsteroidListAdapter()
    }
}