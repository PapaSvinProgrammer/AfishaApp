package com.example.afishaapp.data.http

import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.module.movie.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("/public-api/v1.4/movies/?order_by=title")
    suspend fun getMoviesOrderByTitle(
        @Query("location") location: String,
        @Query("actual_since") actualTime: Int,
        @Query("fields") fields: String = "id,title,poster,imdb_rating,year"
    ): MovieResponse

    @GET("/public-api/v1.4/movies/?order_by=-year")
    suspend fun getMoviesOrderByYear(
        @Query("location") location: String,
        @Query("actual_since") actualTime: Int,
        @Query("fields") fields: String = "id,title,poster,imdb_rating,year"
    ): MovieResponse

    @GET("/public-api/v1.4/movies/?order_by=-imdb_rating")
    suspend fun getMoviesOrderByRating(
        @Query("location") location: String,
        @Query("actual_since") actualTime: Int,
        @Query("fields") fields: String = "id,title,poster,imdb_rating,year"
    ): MovieResponse

    @GET("public-api/v1.4/movies/{movieId}")
    suspend fun getMovieInfo(@Path("movieId") movieId: Int): Movie
}