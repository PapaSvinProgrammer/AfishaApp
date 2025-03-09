package com.example.afishaapp.app.support

import android.util.Log
import com.example.afishaapp.data.module.search.DateRange
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.Locale

private const val DEFAULT_RESPONSE = "Уточняйте на сайте"

object ConvertDate {
    fun convertStartDate(dates: List<DateRange>): String {
        try {
            val last = dates.last()

            if (last.start < 0) {
                return DEFAULT_RESPONSE
            }

            return convertDate(last.start)
        } catch (e: NoSuchElementException) {
            return DEFAULT_RESPONSE
        }
    }

    fun convertListDateRange(dates: List<DateRange>): String {
        try {
            val last = dates.last()

            if (last.start < 0) {
                return DEFAULT_RESPONSE
            }

            val time = (last.end - last.start) / 1000

            return convertDurationMilliseconds(time.toInt())
        } catch (e: NoSuchElementException) {
            return DEFAULT_RESPONSE
        }
    }

    fun convertDatePosted(time: Long): String {
        val sdf = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())

        return sdf.format(time * 1000)
    }

    fun convertDurationMinutes(time: Int): String {
        val hours = time / 60
        val minutes = time % 60

        return generateText(hours, minutes)
    }

    fun convertShowTime(time: Long): String {
        val instant = Instant.ofEpochSecond(time)
        val localZone = ZoneOffset.systemDefault()

        val formatter = DateTimeFormatter.ofPattern("HH:mm").withZone(localZone)

        return formatter.format(instant)
    }

    private fun convertDate(time: Long): String {
        val sdf = SimpleDateFormat("dd MMMM", Locale.getDefault())

        return sdf.format(time * 1000)
    }

    private fun convertDurationMilliseconds(time: Int): String {
        val hours = time / 3600
        val minutes = time / 60

        return generateText(hours, minutes)
    }

    private fun generateText(hours: Int, minutes: Int): String {
        val convertHour = ConvertCountTitle.convertHoursCount(hours)
        val convertMinute = ConvertCountTitle.convertMinutesCount(minutes)

        if (hours > 0 && minutes > 0) {
            return "$convertHour $convertMinute"
        }

        if (hours > 0) {
            return convertHour
        }

        if (minutes > 0) {
            return convertMinute
        }

        return DEFAULT_RESPONSE
    }
}