package com.example.afishaapp.data.repository

import com.example.afishaapp.data.http.CategoryService
import com.example.afishaapp.data.module.Category
import com.example.afishaapp.domain.repository.CategoryRepository
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
) : CategoryRepository {
    override suspend fun getCategoriesPlace(): List<Category> {
        return retrofit.create<CategoryService>().getCategoriesPlace()
    }

    override suspend fun getCategoriesEvent(): List<Category> {
        return retrofit.create<CategoryService>().getCategoriesEvent()
    }
}