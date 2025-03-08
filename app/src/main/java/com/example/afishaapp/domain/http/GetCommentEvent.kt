package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.comment.CommentResponse
import com.example.afishaapp.domain.repository.http.CommentRepository
import javax.inject.Inject

class GetCommentEvent @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend fun getAsc(eventId: Int, page: Int = 1): CommentResponse? {
        if (eventId <= 0) {
            return null
        }

        val queryParameters = QueryParameters(
            id = eventId,
            page = page
        )

        return commentRepository.getEventCommentAsc(queryParameters)
    }

    suspend fun getDesc(eventId: Int, page: Int = 1): CommentResponse? {
        if (eventId <= 0) {
            return null
        }

        val queryParameters = QueryParameters(
            id = eventId,
            page = page
        )

        return commentRepository.getEventCommentDesc(queryParameters)
    }
}