package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.module.movie.MovieResponse
import com.example.afishaapp.domain.repository.http.MovieRepository
import javax.inject.Inject

class GetMovie @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun getMoviesByRating(locationSlug: String): MovieResponse? {
        val currentTime = System.currentTimeMillis() / 1000

        return movieRepository.getMoviesOrderByRating(
            locationSlug = locationSlug,
            actualTime = currentTime.toInt()
        )
    }

    suspend fun getMoviesByTitle(locationSlug: String): MovieResponse? {
        val currentTime = System.currentTimeMillis() / 1000

        return movieRepository.getMoviesOrderByTitle(
            locationSlug = locationSlug,
            actualTime = currentTime.toInt()
        )
    }

    suspend fun getMoviesByYear(locationSlug: String): MovieResponse? {
        val currentTime = System.currentTimeMillis() / 1000

        return movieRepository.getMoviesOrderByYear(
            locationSlug = locationSlug,
            actualTime = currentTime.toInt()
        )
    }

    suspend fun getMovieInfo(movieId: Int): Movie? {
        if (movieId <= 0) {
            return null
        }

        return movieRepository.getMovieInfo(movieId)
    }
}