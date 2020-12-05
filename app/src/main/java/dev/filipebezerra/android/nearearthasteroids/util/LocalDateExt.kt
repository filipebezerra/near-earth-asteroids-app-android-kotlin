package dev.filipebezerra.android.nearearthasteroids.util

import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object LocalDateExt {
    fun dateNowFormatted(): String = LocalDate.now().toIsoLocalDate()
}

fun LocalDate.toIsoLocalDate(): String = format(DateTimeFormatter.ISO_LOCAL_DATE)

fun LocalDate.toEpochMilli(): Long = atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()