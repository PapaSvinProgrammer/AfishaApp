package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.movieShow.MovieShow
import com.example.afishaapp.data.module.movieShow.MovieShowResponse
import com.example.afishaapp.domain.repository.http.MovieShowingRepository
import javax.inject.Inject

class GetMovieShow @Inject constructor(
    private val movieShowingRepository: MovieShowingRepository
) {
    suspend fun getMoviesShow(): MovieShowResponse? {
        val currentTime = System.currentTimeMillis() / 1000

        return movieShowingRepository.getMoviesShow(currentTime)
    }

    suspend fun fetMovieShowInfo(showingId: Int): MovieShow? {
        if (showingId <= 0) {
            return null
        }

        return movieShowingRepository.getMovieShowInfo(showingId)
    }
}