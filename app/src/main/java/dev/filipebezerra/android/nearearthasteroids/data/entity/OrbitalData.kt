package dev.filipebezerra.android.nearearthasteroids.data.entity

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class OrbitalData(@Json(name = "orbit_class") val orbitClass: OrbitClass) : Parcelable

@Parcelize
data class OrbitClass(@Json(name = "orbit_class_description") val description: String) : Parcelable
