package com.example.afishaapp.app.navigation

data class BarItem <T: Any> (
    val name: Int,
    val image: Int,
    val imageFill: Int,
    val route: T
)