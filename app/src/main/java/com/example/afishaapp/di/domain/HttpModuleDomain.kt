package com.example.afishaapp.di.domain

import com.example.afishaapp.domain.http.GetAgent
import com.example.afishaapp.domain.http.GetEvent
import com.example.afishaapp.domain.http.Search
import com.example.afishaapp.domain.repository.http.AgentRepository
import com.example.afishaapp.domain.repository.http.EventRepository
import com.example.afishaapp.domain.repository.http.SearchRepository
import dagger.Module
import dagger.Provides

@Module
interface HttpModuleDomain {
    companion object {
        @Provides
        fun provideGetAgent(repository: AgentRepository): GetAgent {
            return GetAgent(repository)
        }

        @Provides
        fun provideSearch(repository: SearchRepository): Search {
            return Search(repository)
        }

        @Provides
        fun provideGetEvent(repository: EventRepository): GetEvent {
            return GetEvent(repository)
        }
    }
}