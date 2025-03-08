package com.example.afishaapp.domain.repository.http

import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.comment.CommentResponse

interface CommentRepository {
    suspend fun getMovieCommentAsc(queryParameters: QueryParameters): CommentResponse?
    suspend fun getMovieCommentDesc(queryParameters: QueryParameters): CommentResponse?

    suspend fun getEventCommentAsc(queryParameters: QueryParameters): CommentResponse?
    suspend fun getEventCommentDesc(queryParameters: QueryParameters): CommentResponse?
}