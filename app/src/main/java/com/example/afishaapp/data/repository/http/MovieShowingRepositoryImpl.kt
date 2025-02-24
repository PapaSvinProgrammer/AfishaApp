package com.example.afishaapp.data.repository.http

import com.example.afishaapp.data.http.MovieShowingService
import com.example.afishaapp.data.module.movieShow.MovieShow
import com.example.afishaapp.data.module.movieShow.MovieShowResponse
import com.example.afishaapp.domain.repository.http.MovieShowingRepository
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class MovieShowingRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
): MovieShowingRepository {
    override suspend fun getMoviesShow(currentTime: Long): MovieShowResponse? {
        return try {
            retrofit.create<MovieShowingService>().getMovieShows(currentTime)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getMovieShowInfo(showingId: Int): MovieShow? {
        return try {
            retrofit.create<MovieShowingService>().getMovieShowInfo(showingId)
        } catch (e: Exception) {
            null
        }
    }
}