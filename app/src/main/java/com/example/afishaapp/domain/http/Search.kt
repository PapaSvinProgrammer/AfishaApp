package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.search.SearchResponse
import com.example.afishaapp.domain.repository.http.SearchRepository
import javax.inject.Inject

class Search @Inject constructor(
    private val searchRepository: SearchRepository
) {
    suspend fun search(text: String, place: String = ""): SearchResponse? {
        if (text.length <= 2) {
            return null
        }

        val queryParameters = QueryParameters(
            searchText = text,
            locationSlug = place
        )

        return searchRepository.search(queryParameters)
    }

    suspend fun searchEvent(text: String, place: String = ""): SearchResponse? {
        if (text.length <= 2) {
            return null
        }

        val queryParameters = QueryParameters(
            searchText = text,
            locationSlug = place
        )

        return searchRepository.searchEvent(queryParameters)
    }

    suspend fun searchNews(text: String, place: String = ""): SearchResponse? {
        if (text.length <= 2) {
            return null
        }

        val queryParameters = QueryParameters(
            searchText = text,
            locationSlug = place
        )

        return searchRepository.searchNews(queryParameters)
    }

    suspend fun searchPlace(text: String, place: String = ""): SearchResponse? {
        if (text.length <= 2) {
            return null
        }

        val queryParameters = QueryParameters(
            searchText = text,
            locationSlug = place
        )

        return searchRepository.searchPlace(queryParameters)
    }
}