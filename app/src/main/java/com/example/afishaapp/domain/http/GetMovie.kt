package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.domain.repository.http.MovieRepository
import javax.inject.Inject

class GetMovie @Inject constructor(
    private val movieRepository: MovieRepository
) {


    suspend fun getMovieInfo(movieId: Int): Movie? {
        if (movieId <= 0) {
            return null
        }

        return movieRepository.getMovieInfo(movieId)
    }
}