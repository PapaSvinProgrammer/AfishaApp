package com.example.afishaapp.domain.repository.http

import com.example.afishaapp.data.module.Category

interface CategoryRepository {
    suspend fun getCategoriesPlace(): List<Category>
    suspend fun getCategoriesEvent(): List<Category>
}