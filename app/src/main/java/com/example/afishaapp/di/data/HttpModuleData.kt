package com.example.afishaapp.di.data

import com.example.afishaapp.data.repository.http.AgentRepositoryImpl
import com.example.afishaapp.data.repository.http.CategoryRepositoryImpl
import com.example.afishaapp.data.repository.http.CityRepositoryImpl
import com.example.afishaapp.data.repository.http.CommentRepositoryImpl
import com.example.afishaapp.data.repository.http.EventRepositoryImpl
import com.example.afishaapp.data.repository.http.MapImageRepositoryYandex
import com.example.afishaapp.data.repository.http.MovieRepositoryImpl
import com.example.afishaapp.data.repository.http.SearchRepositoryImpl
import com.example.afishaapp.domain.repository.http.AgentRepository
import com.example.afishaapp.domain.repository.http.CategoryRepository
import com.example.afishaapp.domain.repository.http.CityRepository
import com.example.afishaapp.domain.repository.http.CommentRepository
import com.example.afishaapp.domain.repository.http.EventRepository
import com.example.afishaapp.domain.repository.http.MapImageRepository
import com.example.afishaapp.domain.repository.http.MovieRepository
import com.example.afishaapp.domain.repository.http.SearchRepository
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
    fun bindAgentRepositoryImpl(repository: AgentRepositoryImpl): AgentRepository

    @Singleton
    @Binds
    fun bindSearchRepositoryImpl(repository: SearchRepositoryImpl): SearchRepository

    @Singleton
    @Binds
    fun bindMovieRepositoryImpl(repository: MovieRepositoryImpl): MovieRepository

    @Singleton
    @Binds
    fun bindCommentRepositoryImpl(repository: CommentRepositoryImpl): CommentRepository

    @Singleton
    @Binds
    fun bindEventRepositoryImpl(repository: EventRepositoryImpl): EventRepository

    @Singleton
    @Binds
    fun bindMapImageRepositoryYandex(repository: MapImageRepositoryYandex): MapImageRepository
}