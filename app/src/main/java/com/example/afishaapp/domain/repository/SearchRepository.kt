package com.example.afishaapp.domain.repository

import com.example.afishaapp.data.module.SearchResponse

interface SearchRepository {
    suspend fun search(text: String, place: String): SearchResponse
    suspend fun searchEvent(text: String, place: String): SearchResponse
    suspend fun searchPlace(text: String, place: String): SearchResponse
    suspend fun searchList(text: String, place: String): SearchResponse
    suspend fun searchNews(text: String, place: String): SearchResponse
}