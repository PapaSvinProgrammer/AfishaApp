package com.example.afishaapp.data.module.agent

import com.example.afishaapp.data.module.image.ImageItem

data class Agent(
    val id: Int,
    val title: String,
    val favoritesCount: Int,
    val commentsCount: Int,
    val description: String,
    val bodyText: String,
    val images: List<ImageItem>,
    val rank: Int
)