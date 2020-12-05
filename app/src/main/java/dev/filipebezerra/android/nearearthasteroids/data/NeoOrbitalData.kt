package dev.filipebezerra.android.nearearthasteroids.data

import com.squareup.moshi.Json

data class NeoOrbitalData(@Json(name = "orbit_class") val orbitClass: NeoOrbitClass? = null)

data class NeoOrbitClass(@Json(name = "orbit_class_description") val description: String? = null, )
