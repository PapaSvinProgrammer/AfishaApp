package com.example.afishaapp.data.module.event

import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.data.module.image.ImageItem
import com.example.afishaapp.data.module.search.DateRange

data class Event(
    val id: Int = 0,
    val dates: List<DateRange> = listOf(),
    val title: String = "",
    val place: Place? = null,
    val description: String = "",
    val bodyText: String = "",
    val categories: List<String> = listOf(),
    val ageRestriction: String = "",
    val price: String = "",
    val images: List<ImageItem> = listOf(),
    val isFree: Boolean = false,
    val favoritesCount: Int = 0,
    val commentsCount: Int = 0,
    val shortTitle: String = "",
    val tags: List<String> = listOf(),
    val participants: List<String> = listOf()
)