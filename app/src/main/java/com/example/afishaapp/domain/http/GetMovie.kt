package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.module.movie.MovieResponse
import com.example.afishaapp.data.module.movieShow.MovieShowResponse
import com.example.afishaapp.domain.repository.http.MovieRepository
import java.time.LocalDate
import java.time.ZoneId
import javax.inject.Inject

class GetMovie @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend fun getMoviesByRating(locationSlug: String, page: Int = 1): MovieResponse? {
        val currentTime = System.currentTimeMillis() / 1000
        val queryParameters = QueryParameters(
            locationSlug = locationSlug,
            page = page,
            actualSince = currentTime.toInt()
        )

        return movieRepository.getMoviesOrderByRating(queryParameters)
    }

    suspend fun getMoviesByTitle(locationSlug: String, page: Int = 1): MovieResponse? {
        val currentTime = System.currentTimeMillis() / 1000
        val queryParameters = QueryParameters(
            locationSlug = locationSlug,
            page = page,
            actualSince = currentTime.toInt()
        )

        return movieRepository.getMoviesOrderByTitle(queryParameters)
    }

    suspend fun getMoviesByYear(locationSlug: String, page: Int = 1): MovieResponse? {
        val currentTime = System.currentTimeMillis() / 1000
        val queryParameters = QueryParameters(
            locationSlug = locationSlug,
            page = page,
            actualSince = currentTime.toInt()
        )

        return movieRepository.getMoviesOrderByYear(queryParameters)
    }

    suspend fun getMovieInfo(movieId: Int): Movie? {
        if (movieId <= 0) {
            return null
        }

        return movieRepository.getMovieInfo(movieId)
    }
}