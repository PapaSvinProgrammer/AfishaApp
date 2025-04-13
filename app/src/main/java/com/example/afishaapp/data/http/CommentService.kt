package com.example.afishaapp.data.http

import com.example.afishaapp.data.module.comment.CommentResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentService {
    @GET("public-api/v1.4/events/{eventId}/comments/?order_by=date_posted")
    suspend fun getEventCommentAsc(
        @Path("eventId") eventId: Int,
        @Query("page") page: Int = 1
    ): CommentResponse

    @GET("public-api/v1.4/events/{eventId}/comments/?order_by=-date_posted")
    suspend fun getEventCommentDesc(
        @Path("eventId") eventId: Int,
        @Query("page") page: Int = 1
    ): CommentResponse

    @GET("public-api/v1.4/places/{placeId}/comments/?order_by=date_posted")
    suspend fun getPlaceCommentAsc(
        @Path("placeId") placeId: Int,
        @Query("page") page: Int = 1
    ): CommentResponse

    @GET("public-api/v1.4/places/{placeId}/comments/?order_by=-date_posted")
    suspend fun getPlaceCommentDesc(
        @Path("placeId") placeId: Int,
        @Query("page") page: Int = 1
    ): CommentResponse
}