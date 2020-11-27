package dev.filipebezerra.android.nearearthasteroids.ui.asteroidlist

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.ShimmerFrameLayout
import dev.filipebezerra.android.nearearthasteroids.R
import dev.filipebezerra.android.nearearthasteroids.data.entity.NearEarthObject
import dev.filipebezerra.android.nearearthasteroids.data.remote.ApiCallStatus

@BindingAdapter("asteroidList")
fun bindAsteroidList(listView: RecyclerView, nearEarthObjects: List<NearEarthObject>) =
    (listView.adapter as AsteroidListAdapter).submitList(nearEarthObjects)

@BindingAdapter("apiCallStatus")
fun bindApiCallStatus(view: View, apiCallStatus: ApiCallStatus) {
    when (apiCallStatus) {
        ApiCallStatus.LOADING -> when (view.id) {
            R.id.asteroid_list_placeholder -> {
                view.visibility = View.VISIBLE
                (view as ShimmerFrameLayout).startShimmer()
            }
            R.id.asteroid_list,
            R.id.no_network_view -> view.visibility = View.GONE
        }
        ApiCallStatus.SUCCESS -> when (view.id) {
            R.id.asteroid_list_placeholder -> {
                view.visibility = View.GONE
                (view as ShimmerFrameLayout).stopShimmer()
            }
            R.id.asteroid_list -> view.visibility = View.VISIBLE
            R.id.no_network_view -> view.visibility = View.GONE
        }
        ApiCallStatus.ERROR -> when (view.id) {
            R.id.asteroid_list_placeholder -> {
                view.visibility = View.GONE
                (view as ShimmerFrameLayout).stopShimmer()
            }
            R.id.asteroid_list -> view.visibility = View.GONE
            R.id.no_network_view -> view.visibility = View.VISIBLE
        }
    }
}