package com.example.afishaapp.app.support

import com.example.afishaapp.data.module.search.DateRange
import java.text.SimpleDateFormat
import java.util.Locale

object ConvertData {
    fun convertDatesRange(dates: List<DateRange>): String {
        val systemTime = System.currentTimeMillis() / 1000

        for (date in dates) {
            if (date.start >= systemTime) {
                return convertDate(date.start.toInt())
            }
        }

        return "Уточняйте на сайте"
    }

    fun convertMovieDate(time: Int): String {
        val systemTime = System.currentTimeMillis() / 1000

        if (time < systemTime) {
            return "Уже в кино"
        }

        return convertDate(time)
    }

    private fun convertDate(time: Int): String {
        val formatter = SimpleDateFormat("dd MMMM", Locale.getDefault())
        return formatter.format(time)
    }

    fun convertTitle(title: String): String {
        return title.replaceFirstChar(Char::uppercaseChar)
    }

    fun convertPrice(price: String): String {
        if (isFree(price)) {
            return "Бесплатно"
        }

        val splitArray = price.split(" ")
        var result = ""

        for (i in splitArray.indices) {
            val item = splitArray[i]

            if (isWorldException(item)) {
                return convertTitle(price)
            }

            if (isSinceWorld(item)) {
                result += "$item "
            }

            if (isInt(item)) {
                result += item

                if (isInt(splitArray[i + 1])) result += splitArray[i + 1]

                return "$result \u20bd"
            }
        }

        return result
    }

    private fun isSinceWorld(text: String): Boolean {
        return text == "от"
    }

    private fun isInt(text: String): Boolean {
        try {
            text.toInt()
            return true
        } catch (e: NumberFormatException) {
            return false
        }
    }

    private fun isWorldException(text: String): Boolean {
        return text.lowercase().contains("уточн")
    }

    private fun isFree(text: String): Boolean {
        return text.isEmpty()
    }
}