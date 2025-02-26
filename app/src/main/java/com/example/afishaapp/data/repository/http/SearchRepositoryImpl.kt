package com.example.afishaapp.data.repository.http

import com.example.afishaapp.data.http.SearchService
import com.example.afishaapp.data.module.search.SearchResponse
import com.example.afishaapp.domain.repository.http.SearchRepository
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
): SearchRepository {
    override suspend fun search(text: String, place: String): SearchResponse? {
        return try {
            retrofit.create<SearchService>().search(
                text = text,
                place = place
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun searchEvent(text: String, place: String): SearchResponse? {
        return try {
            retrofit.create<SearchService>().searchEvent(
                text = text,
                place = place
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun searchPlace(text: String, place: String): SearchResponse? {
        return try {
            retrofit.create<SearchService>().searchPlace(
                text = text,
                place = place
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun searchList(text: String, place: String): SearchResponse? {
        return try {
            retrofit.create<SearchService>().searchList(
                text = text,
                place = place
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun searchNews(text: String, place: String): SearchResponse? {
        return try {
            retrofit.create<SearchService>().searchNews(
                text = text,
                place = place
            )
        } catch (e: Exception) {
            null
        }
    }
}