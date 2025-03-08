package com.example.afishaapp.data.repository.http

import com.example.afishaapp.data.http.SearchService
import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.search.SearchResponse
import com.example.afishaapp.domain.repository.http.SearchRepository
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
): SearchRepository {
    override suspend fun search(queryParameters: QueryParameters): SearchResponse? {
        return try {
            retrofit.create<SearchService>().search(
                text = queryParameters.searchText,
                location = queryParameters.locationSlug
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun searchEvent(queryParameters: QueryParameters): SearchResponse? {
        return try {
            retrofit.create<SearchService>().searchEvent(
                text = queryParameters.searchText,
                location = queryParameters.locationSlug
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun searchPlace(queryParameters: QueryParameters): SearchResponse? {
        return try {
            retrofit.create<SearchService>().searchPlace(
                text = queryParameters.searchText,
                location = queryParameters.locationSlug
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun searchList(queryParameters: QueryParameters): SearchResponse? {
        return try {
            retrofit.create<SearchService>().searchList(
                text = queryParameters.searchText,
                location = queryParameters.locationSlug
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun searchNews(queryParameters: QueryParameters): SearchResponse? {
        return try {
            retrofit.create<SearchService>().searchNews(
                text = queryParameters.searchText,
                location = queryParameters.locationSlug
            )
        } catch (e: Exception) {
            null
        }
    }
}