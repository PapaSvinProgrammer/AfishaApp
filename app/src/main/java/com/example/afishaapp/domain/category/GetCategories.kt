package com.example.afishaapp.domain.category

import com.example.afishaapp.domain.module.Category
import com.example.afishaapp.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategories @Inject constructor(
    private val categoryRepository: CategoryRepository
) {
    suspend fun getEvent(): List<Category> {
        return categoryRepository.getCategoriesEvent()
    }

    suspend fun getPlace(): List<Category> {
        return categoryRepository.getCategoriesPlace()
    }
}