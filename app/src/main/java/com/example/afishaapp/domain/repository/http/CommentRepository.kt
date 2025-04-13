package com.example.afishaapp.domain.repository.http

import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.comment.CommentResponse

interface CommentRepository {
    suspend fun getEventCommentAsc(queryParameters: QueryParameters): CommentResponse?
    suspend fun getEventCommentDesc(queryParameters: QueryParameters): CommentResponse?

    suspend fun getPlaceCommentAsc(queryParameters: QueryParameters): CommentResponse?
    suspend fun getPlaceCommentDesc(queryParameters: QueryParameters): CommentResponse?
}