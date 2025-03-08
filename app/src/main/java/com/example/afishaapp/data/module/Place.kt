package com.example.afishaapp.data.module

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
    val images: List<ImageItem>
)