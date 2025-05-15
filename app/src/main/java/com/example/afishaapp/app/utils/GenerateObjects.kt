package com.example.afishaapp.app.utils

import com.example.afishaapp.R

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
    "Кино" to R.drawable.ic_cinema,
    "Концерт" to R.drawable.ic_speaker,
    "Театр" to R.drawable.ic_theater,
    "Квест" to R.drawable.ic_detective,
    "Выставка" to R.drawable.ic_image,
    "Для детей" to R.drawable.ic_lolipop,
    "Музей" to R.drawable.ic_museum,
    "Вечеринка" to R.drawable.ic_party,
    "Для бизнеса" to R.drawable.ic_rating,
    "Благотворительность" to R.drawable.ic_charity,
    "Мода и красота" to R.drawable.ic_fashion,
    "Обучающее" to R.drawable.ic_books
)