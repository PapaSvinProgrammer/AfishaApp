package com.example.afishaapp.data.module.movie

import com.example.afishaapp.data.module.Genre
import com.example.afishaapp.data.module.image.ImageItem

data class Movie(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val bodyText: String = "",
    val favoritesCount: Int = 0,
    val genres: List<Genre> = listOf(),
    val commentsCount: Int = 0,
    val originalTitle: String = "",
    val country: String = "",
    val year: Int = 0,
    val runningTime: Int = 0,
    val mpaaRating: String = "",
    val ageRestriction: String = "",
    val stars: String = "",
    val director: String = "",
    val writer: String = "",
    val awards: String = "",
    val images: List<ImageItem> = listOf(),
    val poster: ImageItem? = null,
    val imdbRating: Float = 0f,
    val trailer: String = ""
)