package com.example.afishaapp.data.http

import com.example.afishaapp.data.module.comment.CommentResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface CommentService {
    @GET("public-api/v1.4/movies/{movieId}/comments/?order_by=date_posted")
    suspend fun getMovieCommentAsc(@Path("movieId") movieId: Int): CommentResponse

    @GET("public-api/v1.4/movies/{movieId}/comments/?order_by=-date_posted")
    suspend fun getMovieCommentDesc(@Path("movieId") movieId: Int): CommentResponse
}