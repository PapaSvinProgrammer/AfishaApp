package com.example.afishaapp.domain.repository

import com.example.afishaapp.data.module.City

interface CityRepository {
    suspend fun getCities(): List<City>
}