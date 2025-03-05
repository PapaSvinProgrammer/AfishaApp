package com.example.afishaapp.app.support

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