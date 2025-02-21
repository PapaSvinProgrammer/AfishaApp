package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.search.SearchResponse
import com.example.afishaapp.domain.repository.http.SearchRepository
import javax.inject.Inject

class Search @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend fun search(text: String, place: String = ""): SearchResponse {
        if (text.length <= 2) {
            return DefaultResponse.DEFAULT_SEARCH_RESPONSE
        }

        return searchRepository.search(
            text = text,
            place = place
        )
    }

    suspend fun searchEvent(text: String, place: String = ""): SearchResponse {
        if (text.length <= 2) {
            return DefaultResponse.DEFAULT_SEARCH_RESPONSE
        }

        return searchRepository.searchEvent(
            text = text,
            place = place
        )
    }

    suspend fun searchNews(text: String, place: String = ""): SearchResponse {
        if (text.length <= 2) {
            return DefaultResponse.DEFAULT_SEARCH_RESPONSE
        }

        return searchRepository.searchNews(
            text = text,
            place = place
        )
    }

    suspend fun searchPlace(text: String, place: String = ""): SearchResponse {
        if (text.length <= 2) {
            return DefaultResponse.DEFAULT_SEARCH_RESPONSE
        }

        return searchRepository.searchPlace(
            text = text,
            place = place
        )
    }
}