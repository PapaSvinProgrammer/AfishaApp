package com.example.afishaapp.domain.repository.http

import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.module.movie.MovieResponse

interface MovieRepository {
   suspend fun getMoviesOrderByTitle(): MovieResponse
   suspend fun getMoviesOrderByRating(): MovieResponse
   suspend fun getMoviesOrderByYear(): MovieResponse
   suspend fun getMovieInfo(movieId: Int): Movie
}