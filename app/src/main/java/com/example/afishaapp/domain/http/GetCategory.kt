package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.Category
import com.example.afishaapp.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategory @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend fun getEvent(): List<Category> {
        return categoryRepository.getCategoriesEvent()
    }

    suspend fun getPlace(): List<Category> {
        return categoryRepository.getCategoriesPlace()
    }
}