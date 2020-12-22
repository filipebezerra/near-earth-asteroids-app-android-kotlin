package dev.filipebezerra.android.nearearthasteroids.asteroidlist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import dev.filipebezerra.android.nearearthasteroids.R
import dev.filipebezerra.android.nearearthasteroids.databinding.AsteroidListScreenBinding
import dev.filipebezerra.android.nearearthasteroids.domain.Asteroid
import dev.filipebezerra.android.nearearthasteroids.util.ext.getViewModelFactory
import dev.filipebezerra.android.nearearthasteroids.asteroidlist.AsteroidListScreenDirections.Companion.actionAsteroidListToAsteroidDetail as toAsteroidDetail

class AsteroidListScreen : Fragment() {

    private val asteroidListViewModel: AsteroidListViewModel by viewModels { getViewModelFactory() }

    private lateinit var viewBinding: AsteroidListScreenBinding

    private val navController: NavController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = AsteroidListScreenBinding.inflate(inflater)
        .apply {
            viewBinding = this
            viewModel = asteroidListViewModel
        }
        .also {
            (activity as AppCompatActivity).setSupportActionBar(viewBinding.toolbar)
        }
        .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createAndroidListAdapter()
        viewBinding.lifecycleOwner = viewLifecycleOwner
    }

    private fun createAndroidListAdapter() {
        viewBinding.asteroidList.adapter = AsteroidListAdapter(object : AsteroidItemListener {
            override fun onItemClicked(asteroid: Asteroid) {
                navigateToAsteroidDetail(asteroid)
            }

            override fun onShareClicked(asteroid: Asteroid) {
                shareAsteroid(asteroid)
            }

            override fun onMoreInfoClicked(asteroid: Asteroid) {
                openAsteroidJplWeb(asteroid)
            }
        })
    }

    private fun navigateToAsteroidDetail(asteroid: Asteroid) =
        navController.navigate(toAsteroidDetail(asteroid))

    private fun shareAsteroid(asteroid: Asteroid) = activity?.let { fragmentActivity ->
        ShareCompat.IntentBuilder.from(fragmentActivity)
            .setText(getString(R.string.share_asteroid_text, asteroid.name))
            .setType("text/plain")
            .createChooserIntent()
            .addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT or Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
            .run { startActivity(this) }
    }

    private fun openAsteroidJplWeb(asteroid: Asteroid) = activity?.let { fragmentActivity ->
        CustomTabsIntent.Builder()
            .setUrlBarHidingEnabled(true)
            .setShowTitle(true)
            .setShareState(CustomTabsIntent.SHARE_STATE_ON)
            .setStartAnimations(fragmentActivity, R.anim.slide_in_from_right, R.anim.slide_out_to_left)
            .setExitAnimations(fragmentActivity, R.anim.slide_in_from_left, R.anim.slide_out_to_right)
            .build()
            .run {
                launchUrl(fragmentActivity, Uri.parse(asteroid.nasaJplUrl))
            }
    }
}