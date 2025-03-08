package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.QueryParameters
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

        val queryParameters = QueryParameters(
            id = movieId,
            page = page
        )

        return commentRepository.getMovieCommentAsc(queryParameters)
    }

    suspend fun getDesc(movieId: Int, page: Int = 1): CommentResponse? {
        if (movieId <= 0) {
            return null
        }

        val queryParameters = QueryParameters(
            id = movieId,
            page = page
        )

        return commentRepository.getMovieCommentDesc(queryParameters)
    }
}