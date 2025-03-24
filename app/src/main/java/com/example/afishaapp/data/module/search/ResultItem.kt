package com.example.afishaapp.data.module.search

import com.example.afishaapp.data.module.place.Place
import com.google.gson.annotations.SerializedName

data class ResultItem(
    val id: Int,
    val title: String,
    val ctype: String,
    val favoriteCount: Int,
    val commentsCount: Int,
    val description: String,
    val place: Place,
    @SerializedName("daterange")
    val dateRange: DateRange,
    val address: String
)
