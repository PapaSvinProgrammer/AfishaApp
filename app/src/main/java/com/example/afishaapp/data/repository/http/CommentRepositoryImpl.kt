package com.example.afishaapp.data.repository.http

import com.example.afishaapp.data.http.CommentService
import com.example.afishaapp.data.module.comment.CommentResponse
import com.example.afishaapp.domain.repository.http.CommentRepository
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
): CommentRepository {
    override suspend fun getMovieCommentAsc(movieId: Int): CommentResponse? {
        return try {
            retrofit.create<CommentService>().getMovieCommentAsc(movieId)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getMovieCommentDesc(movieId: Int): CommentResponse? {
        return try {
            retrofit.create<CommentService>().getMovieCommentDesc(movieId)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getEventCommentAsc(eventId: Int): CommentResponse? {
        return try {
            retrofit.create<CommentService>().getEventCommentAsc(eventId)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getEventCommentDesc(eventId: Int): CommentResponse? {
        return try {
            retrofit.create<CommentService>().getEventCommentDesc(eventId)
        } catch (e: Exception) {
            null
        }
    }
}