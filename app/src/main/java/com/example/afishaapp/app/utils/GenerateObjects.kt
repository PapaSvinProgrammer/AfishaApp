package com.example.afishaapp.app.utils

import com.example.afishaapp.R

data class CategoryNode(
    val title: String,
    val slug: String,
    val icon: Int
)

private val imageArray = listOf(
    R.drawable.account_cat,
    R.drawable.account_bear,
    R.drawable.account_panda,
    R.drawable.account_liama,
    R.drawable.account_chicken
)

fun generateAccountImage(): Int {
    return imageArray.random()
}

val subwayIcon = mapOf(
    "msk" to R.drawable.ic_metro_msk,
    "spb" to R.drawable.ic_metro_spb,
    "ekb" to R.drawable.ic_metro_ekb,
    "kzn" to R.drawable.ic_metro_kzn,
    "nnv" to R.drawable.ic_metro_nnv
)

val categoryList = listOf(
    CategoryNode("Кино", "cinema", R.drawable.ic_cinema),
    CategoryNode("Концерт", "concert", R.drawable.ic_speaker),
    CategoryNode("Театр", "theater", R.drawable.ic_theater),
    CategoryNode("Квест", "quest", R.drawable.ic_detective),
    CategoryNode("Выставка", "exhibition", R.drawable.ic_image),
    CategoryNode("Для детей", "kids", R.drawable.ic_lolipop),
    CategoryNode("Экскурсии", "tour", R.drawable.ic_museum),
    CategoryNode("Вечеринка", "party", R.drawable.ic_party),
    CategoryNode("Для бизнеса", "business-events", R.drawable.ic_rating),
    CategoryNode("Благотворительность", "ocial-activity", R.drawable.ic_charity),
    CategoryNode("Мода и красота", "fashion", R.drawable.ic_fashion),
    CategoryNode("Обучающее", "education", R.drawable.ic_books)
)