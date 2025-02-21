package com.example.afishaapp.data.repository.http

import com.example.afishaapp.data.http.CityService
import com.example.afishaapp.data.module.City
import com.example.afishaapp.domain.repository.http.CityRepository
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class CityRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
): CityRepository {
    override suspend fun getCities(): List<City> {
        return retrofit.create<CityService>().getCities()
    }
}