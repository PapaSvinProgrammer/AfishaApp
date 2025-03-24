package com.example.afishaapp.data.module.movieShow

import com.example.afishaapp.data.module.place.Place

data class Show(
    val id: Int,
    val place: Place,
    val priceTime: ArrayList<PriceTime>
)