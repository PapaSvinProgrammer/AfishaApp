package com.example.afishaapp.domain.repository.http

import com.example.afishaapp.data.module.movieShow.MovieShow
import com.example.afishaapp.data.module.movieShow.MovieShowResponse

interface MovieShowingRepository {
    suspend fun getMoviesShow(currentTime: Long): MovieShowResponse?
    suspend fun getMovieShowInfo(showingId: Int): MovieShow?
}