package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.comment.CommentResponse
import com.example.afishaapp.domain.repository.http.CommentRepository
import javax.inject.Inject

class GetCommentPlace @Inject constructor(
    private val commentRepository: CommentRepository
){
    suspend fun getAsc(id: Int, page: Int = 1): CommentResponse? {
        if (id <= 0) {
            return null
        }

        val queryParameters = QueryParameters(id = id, page = page)
        return commentRepository.getPlaceCommentAsc(queryParameters)
    }

    suspend fun getDesc(id: Int, page: Int = 1):CommentResponse? {
        if (id <= 0) {
            return null
        }

        val queryParameters = QueryParameters(id = id, page = page)
        return commentRepository.getPlaceCommentDesc(queryParameters)
    }
}