package com.example.afishaapp.data.repository.http

import com.example.afishaapp.data.http.MovieService
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.module.movie.MovieResponse
import com.example.afishaapp.domain.repository.http.MovieRepository
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
): MovieRepository {
    override suspend fun getMoviesOrderByTitle(
        locationSlug: String,
        actualTime: Int,
        page: Int
    ): MovieResponse? {
        return try {
            retrofit.create<MovieService>().getMoviesOrderByTitle(
                location = locationSlug,
                actualTime = actualTime,
                page = page
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getMoviesOrderByRating(
        locationSlug: String,
        actualTime: Int,
        page: Int
    ): MovieResponse? {
        return try {
            retrofit.create<MovieService>().getMoviesOrderByRating(
                location = locationSlug,
                actualTime = actualTime,
                page = page
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getMoviesOrderByYear(
        locationSlug: String,
        actualTime: Int,
        page: Int
    ): MovieResponse? {
        return try {
            retrofit.create<MovieService>().getMoviesOrderByYear(
                location = locationSlug,
                actualTime = actualTime,
                page = page
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getMovieInfo(movieId: Int): Movie? {
        return try {
            retrofit.create<MovieService>().getMovieInfo(movieId)
        } catch (e: Exception) {
            null
        }
    }
}