package com.example.afishaapp.app.utils

object ConvertCountTitle {
    fun convertLikeCount(count: Int): String {
        val form1 = "лайк"
        val form2 = "лайка"
        val form3 = "лайков"

        return "$count ${pluralForm(count, form1, form2, form3)}"
    }

    fun convertCommentsCount(count: Int): String {
        val form1 = "отзыв"
        val form2 = "отзыва"
        val form3 = "отзывов"

        return "$count ${pluralForm(count, form1, form2, form3)}"
    }


    fun convertHoursCount(count: Int): String {
        val form1 = "час"
        val form2 = "часа"
        val form3 = "часов"

        return "$count ${pluralForm(count, form1, form2, form3)}"
    }

    fun convertMinutesCount(count: Int): String {
        val form1 = "минута"
        val form2 = "минуты"
        val form3 = "минут"

        return "$count ${pluralForm(count, form1, form2, form3)}"
    }

    private fun pluralForm(n: Int, form1: String, form2: String, form3: String): String {
        val remainder10 = n % 10
        val remainder100 = n % 100

        return when {
            remainder10 == 1 && remainder100 != 11 -> form1
            remainder10 in 2..4 && remainder100 !in 12..14 -> form2
            else -> form3
        }
    }
}