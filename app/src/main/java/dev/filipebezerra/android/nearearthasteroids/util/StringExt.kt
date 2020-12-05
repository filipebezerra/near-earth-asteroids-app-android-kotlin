package dev.filipebezerra.android.nearearthasteroids.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toLocalDate(): LocalDate = LocalDate.parse(this, DateTimeFormatter.ISO_LOCAL_DATE)