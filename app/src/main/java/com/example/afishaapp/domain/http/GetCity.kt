package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.City
import com.example.afishaapp.domain.repository.CityRepository
import javax.inject.Inject

class GetCity @Inject constructor (
    private val cityRepository: CityRepository
) {
    suspend fun getCities(): List<City> {
        return cityRepository.getCities()
    }
}