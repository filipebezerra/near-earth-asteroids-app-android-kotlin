package dev.filipebezerra.android.nearearthasteroids.ui.asteroidlist

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.appbar.CollapsingToolbarLayout
import dev.filipebezerra.android.nearearthasteroids.R
import dev.filipebezerra.android.nearearthasteroids.domain.Asteroid
import dev.filipebezerra.android.nearearthasteroids.domain.PictureOfDay

@BindingAdapter("asteroidList")
fun bindAsteroidList(listView: RecyclerView, asteroids: List<Asteroid>?) =
    asteroids?.let { (listView.adapter as AsteroidListAdapter).submitList(asteroids) }

@BindingAdapter("pictureOfDay", "bitmap")
fun ImageView.bindPictureOfDay(
    pictureOfDay: PictureOfDay?,
    bitmap: Boolean? = false,
) = pictureOfDay?.apply {
    when (bitmap) {
        true -> Glide.with(context)
            .asBitmap()
            .load(hdPictureUrl)
            .transition(BitmapTransitionOptions.withCrossFade())
        else -> Glide.with(context)
            .load(pictureOfDay)
            .transition(DrawableTransitionOptions.withCrossFade())
    }.apply {
        placeholder(R.drawable.picture_of_day_placeholder)
        error(R.drawable.picture_of_day_placeholder)
        fallback(R.drawable.picture_of_day_placeholder)
        contentDescription = if (hdPictureUrl == null) context.getString(R.string.picture_of_day_placeholder) else title
    }.run { into(this@bindPictureOfDay) }
}

@BindingAdapter("titlePictureOfDay")
fun CollapsingToolbarLayout.bindTitleOfPictureOfDay(pictureOfDay: PictureOfDay?) =
    pictureOfDay?.let { picture ->
        title = when {
            picture.hdPictureUrl != null -> picture.title
            else -> context.getString(R.string.picture_of_day_placeholder)
        }
    }

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