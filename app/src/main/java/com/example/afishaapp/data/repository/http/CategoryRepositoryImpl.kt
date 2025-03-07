package com.example.afishaapp.data.repository.http

import com.example.afishaapp.data.http.CategoryService
import com.example.afishaapp.data.module.Category
import com.example.afishaapp.domain.repository.http.CategoryRepository
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
) : CategoryRepository {
    override suspend fun getCategoriesPlace(): List<Category> {
        return try {
            retrofit.create<CategoryService>().getCategoriesPlace()
        } catch (e: Exception) {
            listOf()
        }
    }

    override suspend fun getCategoriesEvent(): List<Category> {
        return try {
            retrofit.create<CategoryService>().getCategoriesEvent()
        } catch (e: Exception) {
            listOf()
        }
    }
}