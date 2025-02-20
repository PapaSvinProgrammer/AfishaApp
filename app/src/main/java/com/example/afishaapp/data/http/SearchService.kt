package com.example.afishaapp.data.http

import com.example.afishaapp.data.module.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SearchService {
    @GET("public-api/v1.4/search/?q={text}&location={place}")
    suspend fun search(
        @Path("text") text: String,
        @Path("place") place: String
    ): SearchResponse

    @GET("public-api/v1.4/search/?q={text}&location={place}&ctype=news")
    suspend fun searchNews(
        @Path("text") text: String,
        @Path("place") place: String
    ): SearchResponse

    @GET("public-api/v1.4/search/?q={text}&location={place}&ctype=place")
    suspend fun searchPlace(
        @Path("text") text: String,
        @Path("place") place: String
    ): SearchResponse

    @GET("public-api/v1.4/search/?q={text}&location={place}&ctype=list")
    suspend fun searchList(
        @Path("text") text: String,
        @Path("place") place: String
    ): SearchResponse

    @GET("public-api/v1.4/search/?q={text}&location={place}&ctype=event")
    suspend fun searchEvent(
        @Path("text") text: String,
        @Path("place") place: String
    ): SearchResponse
}