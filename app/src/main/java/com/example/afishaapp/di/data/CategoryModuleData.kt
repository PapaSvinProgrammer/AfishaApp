package com.example.afishaapp.di.data

import com.example.afishaapp.data.repository.CategoryRepositoryImpl
import com.example.afishaapp.domain.repository.CategoryRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface CategoryModuleData {
    @Singleton
    @Binds
    fun bindCategoryRepositoryImpl(repository: CategoryRepositoryImpl): CategoryRepository
}