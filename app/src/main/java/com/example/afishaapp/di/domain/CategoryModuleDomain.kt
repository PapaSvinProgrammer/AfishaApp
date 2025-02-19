package com.example.afishaapp.di.domain

import com.example.afishaapp.domain.category.GetCategories
import com.example.afishaapp.domain.repository.CategoryRepository
import dagger.Module
import dagger.Provides

@Module
interface CategoryModuleDomain {
    companion object {
        @Provides
        fun provideGetCategories(repository: CategoryRepository): GetCategories {
            return GetCategories(repository)
        }
    }
}