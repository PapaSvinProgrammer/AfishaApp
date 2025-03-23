package com.example.afishaapp.domain.repository.http

import com.example.afishaapp.data.module.Coordinate

interface MapImageRepository {
    fun getImageUrl(coordinate: Coordinate): String
}