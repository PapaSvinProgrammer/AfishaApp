package com.example.afishaapp.data.http

import com.example.afishaapp.data.module.movieShow.MovieShow
import com.example.afishaapp.data.module.movieShow.MovieShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieShowingService {
    @GET("public-api/v1.4/movie-showings/")
    suspend fun getMovieShows(
        @Query("actual_since") currentTime: Long,
        @Query("expand") expand: String = "movie"
    ): MovieShowResponse

    @GET("public-api/v1.4/movie-showings/{showingId}/?expand=movie")
    suspend fun getMovieShowInfo(@Path("showingId") showingId: Int): MovieShow
}