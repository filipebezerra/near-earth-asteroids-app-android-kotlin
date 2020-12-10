package dev.filipebezerra.android.nearearthasteroids.ui.asteroidlist

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.filipebezerra.android.nearearthasteroids.domain.Asteroid

@BindingAdapter("asteroidList")
fun bindAsteroidList(listView: RecyclerView, asteroids: List<Asteroid>?) =
    asteroids?.let { (listView.adapter as AsteroidListAdapter).submitList(asteroids) }

/*
@BindingAdapter("apiCallStatus")
fun bindApiCallStatus(view: View, apiCallStatus: ApiCallStatus) {
    when (apiCallStatus) {
        ApiCallStatus.LOADING -> when (view.id) {
            R.id.asteroid_list_placeholder -> {
                view.visibility = View.VISIBLE
                (view as ShimmerFrameLayout).startShimmer()
            }
            R.id.asteroid_list,
            R.id.asteroid_list_error -> view.visibility = View.GONE
        }
        ApiCallStatus.SUCCESS -> when (view.id) {
            R.id.asteroid_list_placeholder -> {
                view.visibility = View.GONE
                (view as ShimmerFrameLayout).stopShimmer()
            }
            R.id.asteroid_list -> view.visibility = View.VISIBLE
            R.id.asteroid_list_error -> view.visibility = View.GONE
        }
        ApiCallStatus.ERROR -> when (view.id) {
            R.id.asteroid_list_placeholder -> {
                view.visibility = View.GONE
                (view as ShimmerFrameLayout).stopShimmer()
            }
            R.id.asteroid_list -> view.visibility = View.GONE
            R.id.asteroid_list_error -> view.visibility = View.VISIBLE
        }
    }
}
 */