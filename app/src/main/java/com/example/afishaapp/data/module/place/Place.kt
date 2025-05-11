package com.example.afishaapp.data.module.place

import com.example.afishaapp.data.module.Coordinate
import com.example.afishaapp.data.module.image.ImageItem
import com.google.gson.annotations.SerializedName

data class Place (
    val id: Int = 0,
    val title: String = "",
    val address: String = "",
    val phone: String = "",
    val location: String = "",
    @SerializedName("coords")
    val coordinates: Coordinate? = null,
    val subway: String = "",
    val images: List<ImageItem>?,
    val description: String = "",
    val bodyText: String = "",
    val favoritesCount: Int = 0,
    val commentsCount: Int = 0,
    val categories: List<String> = listOf(),
    val tags: List<String>? = listOf(),
    val isClosed: Boolean = true,
    val hasParkingLot: Boolean = false
)