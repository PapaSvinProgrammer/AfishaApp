package com.example.afishaapp.domain.repository.http

import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.search.SearchResponse

interface SearchRepository {
    suspend fun search(queryParameters: QueryParameters): SearchResponse?
    suspend fun searchEvent(queryParameters: QueryParameters): SearchResponse?
    suspend fun searchPlace(queryParameters: QueryParameters): SearchResponse?
    suspend fun searchList(queryParameters: QueryParameters): SearchResponse?
    suspend fun searchNews(queryParameters: QueryParameters): SearchResponse?
}