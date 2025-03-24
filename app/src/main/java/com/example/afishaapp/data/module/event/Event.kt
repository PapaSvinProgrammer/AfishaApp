package com.example.afishaapp.data.module.event

import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.data.module.image.ImageItem
import com.example.afishaapp.data.module.search.DateRange

data class Event(
    val id: Int,
    val dates: List<DateRange>,
    val title: String,
    val place: Place?,
    val description: String,
    val bodyText: String,
    val categories: List<String>,
    val ageRestriction: String,
    val price: String,
    val images: List<ImageItem>,
    val isFree: Boolean,
    val favoritesCount: Int,
    val commentsCount: Int,
    val shortTitle: String,
    val tags: List<String>,
    val participants: List<String>
)