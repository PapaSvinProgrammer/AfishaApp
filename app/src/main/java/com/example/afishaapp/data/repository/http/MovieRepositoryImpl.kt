package com.example.afishaapp.data.repository.http

import com.example.afishaapp.data.http.MovieService
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.module.movie.MovieResponse
import com.example.afishaapp.domain.repository.http.MovieRepository
import retrofit2.Retrofit
import retrofit2.create

class MovieRepositoryImpl(
    private val retrofit: Retrofit
): MovieRepository {
    override suspend fun getMoviesOrderByTitle(): MovieResponse? {
        return try {
            retrofit.create<MovieService>().getMoviesOrderByTitle()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getMoviesOrderByRating(): MovieResponse? {
        return try {
            retrofit.create<MovieService>().getMoviesOrderByRating()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getMoviesOrderByYear(): MovieResponse? {
        return try {
            retrofit.create<MovieService>().getMoviesOrderByYear()
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getMovieInfo(movieId: Int): Movie? {
        return try {
            retrofit.create<MovieService>().getMovieInfo(movieId)
        } catch (e: Exception) {
            null
        }
    }
}