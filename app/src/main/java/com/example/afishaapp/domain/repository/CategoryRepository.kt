package com.example.afishaapp.domain.repository

import com.example.afishaapp.domain.module.Category

interface CategoryRepository {
    suspend fun getCategoriesPlace(): List<Category>
    suspend fun getCategoriesEvent(): List<Category>
}