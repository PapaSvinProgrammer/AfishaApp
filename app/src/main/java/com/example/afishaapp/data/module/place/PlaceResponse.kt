package com.example.afishaapp.data.module.place

data class PlaceResponse(
    val id: Int,
    val next: String?,
    val previous: String?,
    val results: List<Place>
)