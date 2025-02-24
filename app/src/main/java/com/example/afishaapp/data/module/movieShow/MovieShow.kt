package com.example.afishaapp.data.module.movieShow

import com.example.afishaapp.data.module.Place
import com.example.afishaapp.data.module.movie.Movie
import com.google.gson.annotations.SerializedName

data class MovieShow(
    val id: Int,
    val place: Place,
    val movie: Movie,
    @SerializedName("datetime")
    val dateTime: Int
)
