package com.example.afishaapp.di.domain

import com.example.afishaapp.domain.http.GetAgent
import com.example.afishaapp.domain.http.GetCategory
import com.example.afishaapp.domain.http.GetCity
import com.example.afishaapp.domain.http.GetEventsDay
import com.example.afishaapp.domain.http.Search
import com.example.afishaapp.domain.repository.AgentRepository
import com.example.afishaapp.domain.repository.CategoryRepository
import com.example.afishaapp.domain.repository.CityRepository
import com.example.afishaapp.domain.repository.EventDayRepository
import com.example.afishaapp.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides

@Module
interface HttpModuleDomain {
    companion object {
        @Provides
        fun provideGetCategories(repository: CategoryRepository): GetCategory {
            return GetCategory(repository)
        }

        @Provides
        fun provideGetCities(repository: CityRepository): GetCity {
            return GetCity(repository)
        }

        @Provides
        fun provideGetEventsDay(repository: EventDayRepository): GetEventsDay {
            return GetEventsDay(repository)
        }

        @Provides
        fun provideGetAgent(repository: AgentRepository): GetAgent {
            return GetAgent(repository)
        }

        @Provides
        fun provideSearch(repository: SearchRepository): Search {
            return Search(repository)
        }
    }
}