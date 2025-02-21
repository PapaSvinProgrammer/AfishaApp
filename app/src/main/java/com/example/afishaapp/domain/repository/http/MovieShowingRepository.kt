package com.example.afishaapp.domain.repository.http

import com.example.afishaapp.data.module.movieShow.MovieShow
import com.example.afishaapp.data.module.movieShow.MovieShowResponse

interface MovieShowingRepository {
    suspend fun getMovieShows(currentTime: Int): MovieShowResponse
    suspend fun getMovieShowInfo(showingId: Int): MovieShow
}