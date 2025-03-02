package com.example.afishaapp.app.support

import com.example.afishaapp.data.module.search.DateRange
import java.text.SimpleDateFormat
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

    fun convertDuration(dates: List<DateRange>): String {
        try {
            val last = dates.last()

            if (last.start < 0) {
                return DEFAULT_RESPONSE
            }

            return convertStartDate(last)
        } catch (e: NoSuchElementException) {
            return DEFAULT_RESPONSE
        }
    }

    private fun convertDate(time: Long): String {
        val sdf = SimpleDateFormat("dd MMMM", Locale.getDefault())

        return sdf.format(time * 1000)
    }

    private fun convertStartDate(dateRange: DateRange): String {
        val time = (dateRange.end - dateRange.start) / 1000

        val hours = (time / 3600).toInt()
        val minutes = (time / 60).toInt()

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