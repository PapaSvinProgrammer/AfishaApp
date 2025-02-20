package com.example.afishaapp.di.data

import com.example.afishaapp.data.repository.AgentRepositoryImpl
import com.example.afishaapp.data.repository.CategoryRepositoryImpl
import com.example.afishaapp.data.repository.CityRepositoryImpl
import com.example.afishaapp.data.repository.EventDayRepositoryImpl
import com.example.afishaapp.data.repository.SearchRepositoryImpl
import com.example.afishaapp.domain.repository.AgentRepository
import com.example.afishaapp.domain.repository.CategoryRepository
import com.example.afishaapp.domain.repository.CityRepository
import com.example.afishaapp.domain.repository.EventDayRepository
import com.example.afishaapp.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface HttpModuleData {
    @Singleton
    @Binds
    fun bindCategoryRepositoryImpl(repository: CategoryRepositoryImpl): CategoryRepository

    @Singleton
    @Binds
    fun bindCityRepositoryImpl(repository: CityRepositoryImpl): CityRepository

    @Singleton
    @Binds
    fun bindEventDayRepositoryImpl(repository: EventDayRepositoryImpl): EventDayRepository

    @Singleton
    @Binds
    fun bindAgentRepositoryImpl(repository: AgentRepositoryImpl): AgentRepository

    @Singleton
    @Binds
    fun bindSearchRepositoryImpl(repository: SearchRepositoryImpl): SearchRepository
}