package com.example.afishaapp.domain.repository.http

import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.module.movie.MovieResponse
import com.example.afishaapp.data.module.movieShow.MovieShowResponse

interface MovieRepository {
   suspend fun getMoviesOrderByTitle(queryParameters: QueryParameters): MovieResponse?
   suspend fun getMoviesOrderByRating(queryParameters: QueryParameters): MovieResponse?
   suspend fun getMoviesOrderByYear(queryParameters: QueryParameters): MovieResponse?

   suspend fun getMovieInfo(movieId: Int): Movie?
   suspend fun getMovieShows(queryParameters: QueryParameters): MovieShowResponse?
}