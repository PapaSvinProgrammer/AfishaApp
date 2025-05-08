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