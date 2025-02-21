package com.example.afishaapp.data.repository.http

import com.example.afishaapp.data.http.CommentService
import com.example.afishaapp.data.module.comment.CommentResponse
import com.example.afishaapp.domain.repository.http.CommentRepository
import retrofit2.Retrofit
import retrofit2.create

class CommentRepositoryImpl(
    private val retrofit: Retrofit
): CommentRepository {
    override suspend fun getMovieCommentAsc(movieId: Int): CommentResponse {
        return retrofit.create<CommentService>().getMovieCommentAsc(movieId)
    }

    override suspend fun getMovieCommentDesc(movieId: Int): CommentResponse {
        return retrofit.create<CommentService>().getMovieCommentDesc(movieId)
    }
}