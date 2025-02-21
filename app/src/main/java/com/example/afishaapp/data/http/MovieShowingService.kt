package com.example.afishaapp.data.http

import com.example.afishaapp.data.module.movieShow.MovieShow
import com.example.afishaapp.data.module.movieShow.MovieShowResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieShowingService {
    @GET("public-api/v1.4/movie-showings/?expand=movie&actual_since={currentTime}")
    suspend fun getMovieShows(@Path("currentTime") currentTime: Int): MovieShowResponse

    @GET("public-api/v1.4/movie-showings/{showingId}/?expand=movie")
    suspend fun getMovieShowInfo(@Path("showingId") showingId: Int): MovieShow
}