package com.example.afishaapp.app.support

import com.example.afishaapp.data.module.search.DateRange

object ConvertData {
    fun convertDatesRange(dates: List<DateRange>): String {
        return "ASD"
    }

    fun convertDate(times: Int): String? {
        if (times < 0) {
            return null
        }

        return "ASD"
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