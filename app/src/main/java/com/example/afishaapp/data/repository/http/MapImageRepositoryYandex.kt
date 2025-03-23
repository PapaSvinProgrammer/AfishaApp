package com.example.afishaapp.data.repository.http

import com.example.afishaapp.BuildConfig
import com.example.afishaapp.data.module.Coordinate
import com.example.afishaapp.domain.repository.http.MapImageRepository
import javax.inject.Inject

private const val MAIN = "https://static-maps.yandex.ru/v1?"

class MapImageRepositoryYandex @Inject constructor(): MapImageRepository {
    override fun getImageUrl(coordinate: Coordinate): String {
        var imageUrl = MAIN

        imageUrl += "apikey=${BuildConfig.STATIC_API_KEY}&"
        imageUrl += "ll=${coordinate.lon},${coordinate.lat}&"
        imageUrl += "spn=0.016457,0.00619&"
        imageUrl += "pt=${coordinate.lon},${coordinate.lat},pm2rdl"

        return imageUrl
    }
}