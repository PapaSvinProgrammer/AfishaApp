package com.example.afishaapp.data.http

import com.example.afishaapp.data.module.City
import retrofit2.http.GET

interface CityService {
    @GET("public-api/v1.4/locations")
    suspend fun getCities(): List<City>
}