package com.example.afishaapp.data.http

import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.module.movie.MovieResponse
import com.example.afishaapp.data.module.movieShow.MovieShowResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("/public-api/v1.4/movies/?order_by=title")
    suspend fun getMoviesOrderByTitle(
        @Query("location") location: String,
        @Query("actual_since") actualTime: Int,
        @Query("page") page: Int,
        @Query("fields") fields: String = "id,title,poster,imdb_rating,year",
        @Query("expand") expand: String = "poster"
    ): MovieResponse

    @GET("/public-api/v1.4/movies/?order_by=-year")
    suspend fun getMoviesOrderByYear(
        @Query("location") location: String,
        @Query("actual_since") actualTime: Int,
        @Query("page") page: Int,
        @Query("fields") fields: String = "id,title,poster,imdb_rating,year",
        @Query("expand") expand: String = "poster"
    ): MovieResponse

    @GET("/public-api/v1.4/movies/?order_by=-imdb_rating")
    suspend fun getMoviesOrderByRating(
        @Query("location") location: String,
        @Query("actual_since") actualTime: Int,
        @Query("page") page: Int,
        @Query("fields") fields: String = "id,title,poster,imdb_rating,year",
        @Query("expand") expand: String = "poster"
    ): MovieResponse

    @GET("public-api/v1.4/movies/{movieId}")
    suspend fun getMovieInfo(
        @Path("movieId") movieId: Int,
        @Query("expand") expand: String = "images,poster"
    ): Movie

    @GET("public-api/v1.4/movies/{movieId}/showings/")
    suspend fun getMovieShowing(
        @Path("movieId") movieId: Int,
        @Query("actual_since") actualSince: Int,
        @Query("actual_until") actualUntil: Int,
        @Query("location") location: String,
        @Query("page") page: Int = 1,
        @Query("expand") expand: String = "place",
        @Query("order_by") orderBy: String = "place, datetime",
        @Query("page_size") pageSize: Int = 100
    ): MovieShowResponse
}