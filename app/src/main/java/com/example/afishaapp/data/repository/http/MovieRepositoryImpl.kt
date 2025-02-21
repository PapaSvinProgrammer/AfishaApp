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
    override suspend fun getMoviesOrderByTitle(): MovieResponse {
        return retrofit.create<MovieService>().getMoviesOrderByTitle()
    }

    override suspend fun getMoviesOrderByRating(): MovieResponse {
        return retrofit.create<MovieService>().getMoviesOrderByRating()
    }

    override suspend fun getMoviesOrderByYear(): MovieResponse {
        return retrofit.create<MovieService>().getMoviesOrderByYear()
    }

    override suspend fun getMovieInfo(movieId: Int): Movie {
        return retrofit.create<MovieService>().getMovieInfo(movieId)
    }
}