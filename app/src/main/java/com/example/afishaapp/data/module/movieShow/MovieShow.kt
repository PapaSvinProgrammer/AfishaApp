package com.example.afishaapp.data.module.movieShow

import com.example.afishaapp.data.module.Place
import com.example.afishaapp.data.module.image.ImageItem
import com.google.gson.annotations.SerializedName

data class MovieShow(
    val id: Int,
    val title: String,
    val poster: ImageItem,
    val place: Place,
    @SerializedName("datetime")
    val dateTime: Int
)
