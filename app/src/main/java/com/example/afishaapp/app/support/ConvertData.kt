package com.example.afishaapp.app.support

object ConvertData {
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

    fun convertAgeRestriction(text: String): String {
        try {
            text.toInt()
            return "$text+"
        }
        catch (e: NumberFormatException) {
            return text
        }
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