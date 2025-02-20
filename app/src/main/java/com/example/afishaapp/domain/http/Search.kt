package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.SearchResponse
import com.example.afishaapp.domain.repository.SearchRepository
import javax.inject.Inject

class Search @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend fun search(text: String, place: String = ""): SearchResponse {
        return searchRepository.search(
            text = text,
            place = place
        )
    }

    suspend fun searchEvent(text: String, place: String = ""): SearchResponse {
        return searchRepository.searchEvent(
            text = text,
            place = place
        )
    }

    suspend fun searchNews(text: String, place: String = ""): SearchResponse {
        return searchRepository.searchNews(
            text = text,
            place = place
        )
    }

    suspend fun searchPlace(text: String, place: String = ""): SearchResponse {
        return searchRepository.searchPlace(
            text = text,
            place = place
        )
    }
}