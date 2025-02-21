package com.example.afishaapp.domain.repository.http

import com.example.afishaapp.data.module.City

interface CityRepository {
    suspend fun getCities(): List<City>
}