package com.example.afishaapp.data.repository.http

import com.example.afishaapp.data.http.CommentService
import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.comment.CommentResponse
import com.example.afishaapp.domain.repository.http.CommentRepository
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class CommentRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
): CommentRepository {
    override suspend fun getMovieCommentAsc(queryParameters: QueryParameters): CommentResponse? {
        return try {
            retrofit.create<CommentService>().getMovieCommentAsc(
                movieId = queryParameters.id,
                page = queryParameters.page
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getMovieCommentDesc(queryParameters: QueryParameters): CommentResponse? {
        return try {
            retrofit.create<CommentService>().getMovieCommentDesc(
                movieId = queryParameters.id,
                page = queryParameters.page
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getEventCommentAsc(queryParameters: QueryParameters): CommentResponse? {
        return try {
            retrofit.create<CommentService>().getEventCommentAsc(
                eventId = queryParameters.id,
                page = queryParameters.page
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getEventCommentDesc(queryParameters: QueryParameters): CommentResponse? {
        return try {
            retrofit.create<CommentService>().getEventCommentDesc(
                eventId = queryParameters.id,
                page = queryParameters.page
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getPlaceCommentAsc(queryParameters: QueryParameters): CommentResponse? {
        TODO("Not yet implemented")
    }

    override suspend fun getPlaceCommentDesc(queryParameters: QueryParameters): CommentResponse? {
        TODO("Not yet implemented")
    }
}