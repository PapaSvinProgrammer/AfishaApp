package com.example.afishaapp.domain.repository.room

import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.room.likeMovie.MovieEntity
import kotlinx.coroutines.flow.Flow

interface LikeMovieRepository {
    suspend fun insert(movie: Movie)
    suspend fun delete(movieId: Int)
    suspend fun getByDefault(): List<MovieEntity>
    suspend fun getByRating(): List<MovieEntity>
    suspend fun getByYear(): List<MovieEntity>
    suspend fun getMovieById(movieId: Int): MovieEntity?
    fun getCount(): Flow<Int>
}