package com.example.afishaapp.data.module.movieShow

data class MovieShowResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<MovieShow>
)