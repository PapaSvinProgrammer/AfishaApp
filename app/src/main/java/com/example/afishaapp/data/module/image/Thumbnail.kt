package com.example.afishaapp.data.module.image

import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("640x384")
    val highImage: String,
    @SerializedName("144x96")
    val lowImage: String
)