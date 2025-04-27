package com.example.afishaapp.domain.repository.room

import com.example.afishaapp.data.room.likeMovie.MovieEntity

interface LikeMovieRepository {
    suspend fun insert(movieEntity: MovieEntity)
    suspend fun delete(movieId: Int)
    suspend fun getByDefault(): List<MovieEntity>
    suspend fun getByRating(): List<MovieEntity>
    suspend fun getByYear(): List<MovieEntity>
    suspend fun search(q: String): List<MovieEntity>
}