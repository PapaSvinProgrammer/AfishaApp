package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.comment.CommentResponse
import com.example.afishaapp.domain.repository.http.CommentRepository
import javax.inject.Inject

class GetCommentMovie @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend fun getAsc(movieId: Int, page: Int = 1): CommentResponse? {
        if (movieId <= 0) {
            return null
        }

        return commentRepository.getMovieCommentAsc(
            movieId = movieId,
            page = page
        )
    }

    suspend fun getDesc(movieId: Int, page: Int = 1): CommentResponse? {
        if (movieId <= 0) {
            return null
        }

        return commentRepository.getMovieCommentDesc(
            movieId = movieId,
            page = page
        )
    }
}