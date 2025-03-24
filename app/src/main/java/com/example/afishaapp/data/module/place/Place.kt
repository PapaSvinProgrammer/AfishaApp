package com.example.afishaapp.data.module.place

import com.example.afishaapp.data.module.Coordinate
import com.example.afishaapp.data.module.image.ImageItem
import com.google.gson.annotations.SerializedName

data class Place (
    val id: Int,
    val title: String,
    val address: String,
    val phone: String,
    val location: String,
    @SerializedName("coords")
    val coordinates: Coordinate,
    val subway: String,
    val images: List<ImageItem>?,
    val description: String,
    val bodyText: String,
    val favoritesCount: Int,
    val commentsCount: Int,
    val categories: List<String>,
    val tags: List<String>,
    val isClosed: Boolean,
    val hasParkingLot: Boolean
)