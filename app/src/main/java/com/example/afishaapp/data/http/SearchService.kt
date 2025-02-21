package com.example.afishaapp.data.http

import com.example.afishaapp.data.module.search.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchService {
    @GET("public-api/v1.4/search/")
    suspend fun search(
        @Query("q") text: String,
        @Query("location") place: String
    ): SearchResponse

    @GET("public-api/v1.4/search/?ctype=news")
    suspend fun searchNews(
        @Query("q") text: String,
        @Query("location") place: String
    ): SearchResponse

    @GET("public-api/v1.4/search/?ctype=place")
    suspend fun searchPlace(
        @Query("q") text: String,
        @Query("location") place: String
    ): SearchResponse

    @GET("public-api/v1.4/search/?ctype=list")
    suspend fun searchList(
        @Query("q") text: String,
        @Query("location") place: String
    ): SearchResponse

    @GET("public-api/v1.4/search/?ctype=event")
    suspend fun searchEvent(
        @Query("q") text: String,
        @Query("location") place: String
    ): SearchResponse
}