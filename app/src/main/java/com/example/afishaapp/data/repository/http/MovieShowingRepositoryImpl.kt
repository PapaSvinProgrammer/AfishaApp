package com.example.afishaapp.data.repository.http

import com.example.afishaapp.data.http.MovieShowingService
import com.example.afishaapp.data.module.movieShow.MovieShow
import com.example.afishaapp.data.module.movieShow.MovieShowResponse
import com.example.afishaapp.domain.repository.http.MovieShowingRepository
import retrofit2.Retrofit
import retrofit2.create

class MovieShowingRepositoryImpl(
    private val retrofit: Retrofit
): MovieShowingRepository {
    override suspend fun getMovieShows(currentTime: Int): MovieShowResponse {
        return retrofit.create<MovieShowingService>().getMovieShows(currentTime)
    }

    override suspend fun getMovieShowInfo(showingId: Int): MovieShow {
        return retrofit.create<MovieShowingService>().getMovieShowInfo(showingId)
    }
}