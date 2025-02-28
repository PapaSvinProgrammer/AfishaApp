package com.example.afishaapp.domain.repository.http

import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.module.movie.MovieResponse

interface MovieRepository {
   suspend fun getMoviesOrderByTitle(
      locationSlug: String,
      actualTime: Int,
      page: Int
   ): MovieResponse?

   suspend fun getMoviesOrderByRating(
      locationSlug: String,
      actualTime: Int,
      page: Int
   ): MovieResponse?

   suspend fun getMoviesOrderByYear(
      locationSlug: String,
      actualTime: Int,
      page: Int
   ): MovieResponse?

   suspend fun getMovieInfo(movieId: Int): Movie?
}