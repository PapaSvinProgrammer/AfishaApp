package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.comment.CommentResponse
import com.example.afishaapp.domain.repository.http.CommentRepository
import javax.inject.Inject

class GetCommentMovie @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend fun getAsc(movieId: Int): CommentResponse? {
        if (movieId <= 0) {
            return null
        }

        return commentRepository.getMovieCommentAsc(movieId)
    }

    suspend fun getDesc(movieId: Int): CommentResponse? {
        if (movieId <= 0) {
            return null
        }

        return commentRepository.getMovieCommentDesc(movieId)
    }
}