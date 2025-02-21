package com.example.afishaapp.data.module.movie

data class MovieResponse(
    val id: Int,
    val next: String,
    val previous: String,
    val results: List<Movie>
)