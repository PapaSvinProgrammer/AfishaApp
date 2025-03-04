package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.comment.CommentResponse
import com.example.afishaapp.domain.repository.http.CommentRepository
import javax.inject.Inject

class GetCommentEvent @Inject constructor(
    private val commentRepository: CommentRepository
) {
    suspend fun getAsc(eventId: Int): CommentResponse? {
        if (eventId <= 0) {
            return null
        }

        return commentRepository.getEventCommentAsc(eventId)
    }

    suspend fun getDesc(eventId: Int): CommentResponse? {
        if (eventId <= 0) {
            return null
        }

        return commentRepository.getEventCommentDesc(eventId)
    }
}