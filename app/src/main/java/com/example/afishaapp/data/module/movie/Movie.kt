package com.example.afishaapp.data.module.movie

import com.example.afishaapp.data.module.Genre
import com.example.afishaapp.data.module.image.ImageItem

data class Movie(
    val id: Int,
    val title: String,
    val description: String,
    val bodyText: String,
    val favoritesCount: Int,
    val genres: List<Genre>,
    val commentsCount: Int,
    val originalTitle: String,
    val country: String,
    val year: Int,
    val runningTime: Int,
    val mpaaRating: String,
    val ageRestriction: String,
    val stars: String,
    val director: String,
    val writer: String,
    val awards: String,
    val images: List<ImageItem>,
    val poster: ImageItem,
    val imdbRating: Float,
    val trailer: String
)