package com.example.afishaapp.data.module.movieShow

import com.example.afishaapp.data.module.place.Place
import com.google.gson.annotations.SerializedName

data class MovieShow(
    val id: Int,
    val place: Place,
    @SerializedName("datetime")
    val dateTime: Int,
    val price: String?
)
