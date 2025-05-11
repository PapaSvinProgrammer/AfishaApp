package com.example.afishaapp.app.utils.convertData

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.afishaapp.data.module.search.DateRange
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Date
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

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    fun convertSelectTime(time: Long): String {
        val instant = Instant.ofEpochSecond(time)
        val localZone = ZoneOffset.systemDefault()

        val localDate = LocalDate.ofInstant(instant, localZone)
        val dayOfWeek = localDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())

        val formatter = DateTimeFormatter.ofPattern("d MMMM").withZone(localZone)

        return "${formatter.format(instant)}, $dayOfWeek"
    }

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    fun addDaysToCurrentDate(days: Int): String {
        val localDate = LocalDate.now().atStartOfDay(ZoneOffset.systemDefault())
        val time = localDate.plusDays(days.toLong()).toEpochSecond()

        return convertSelectTime(time)
    }

    fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formatter.format(Date(millis))
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