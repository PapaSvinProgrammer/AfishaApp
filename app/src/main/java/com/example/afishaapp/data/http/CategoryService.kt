package com.example.afishaapp.data.http

import com.example.afishaapp.domain.module.Category
import retrofit2.http.GET

interface CategoryService {
    @GET("public-api/v1.4/event-categories/")
    suspend fun getCategoriesEvent(): List<Category>

    @GET("public-api/v1.4/place-categories")
    suspend fun getCategoriesPlace(): List<Category>
}