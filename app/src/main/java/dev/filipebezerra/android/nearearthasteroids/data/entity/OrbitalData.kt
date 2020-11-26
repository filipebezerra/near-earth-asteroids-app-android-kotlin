package dev.filipebezerra.android.nearearthasteroids.data.entity

import com.squareup.moshi.Json

data class OrbitalData(@Json(name = "orbit_class") val orbitClass: OrbitClass)

data class OrbitClass(@Json(name = "orbit_class_description") val description: String)
