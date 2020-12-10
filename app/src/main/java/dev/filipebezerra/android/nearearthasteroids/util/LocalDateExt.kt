package dev.filipebezerra.android.nearearthasteroids.util

import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object LocalDateExt {
    fun dateNowAsIsoLocalDate(): String = LocalDate.now().asIsoLocalDate()

    fun oneWeekFromNow(): LocalDate = LocalDate.now().plusWeeks(1)
}

fun LocalDate.asIsoLocalDate(): String = format(DateTimeFormatter.ISO_LOCAL_DATE)

fun LocalDate.toEpochMilli(): Long = atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli()