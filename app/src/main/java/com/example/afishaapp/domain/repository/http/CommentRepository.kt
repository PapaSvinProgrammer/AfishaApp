package com.example.afishaapp.domain.repository.http

import com.example.afishaapp.data.module.comment.CommentResponse

interface CommentRepository {
    suspend fun getMovieCommentAsc(movieId: Int, page: Int): CommentResponse?
    suspend fun getMovieCommentDesc(movieId: Int, page: Int): CommentResponse?

    suspend fun getEventCommentAsc(eventId: Int, page: Int): CommentResponse?
    suspend fun getEventCommentDesc(eventId: Int, page: Int): CommentResponse?
}