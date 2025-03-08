package com.example.afishaapp.data.repository.http

import com.example.afishaapp.data.http.MovieService
import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.module.movie.MovieResponse
import com.example.afishaapp.data.module.movieShow.MovieShowResponse
import com.example.afishaapp.domain.repository.http.MovieRepository
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
): MovieRepository {
    override suspend fun getMoviesOrderByTitle(queryParameters: QueryParameters): MovieResponse? {
        return try {
            retrofit.create<MovieService>().getMoviesOrderByTitle(
                location = queryParameters.locationSlug,
                actualTime = queryParameters.actualSince,
                page = queryParameters.page
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getMoviesOrderByRating(queryParameters: QueryParameters): MovieResponse? {
        return try {
            retrofit.create<MovieService>().getMoviesOrderByRating(
                location = queryParameters.locationSlug,
                actualTime = queryParameters.actualSince,
                page =queryParameters.page
            )
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getMoviesOrderByYear(queryParameters: QueryParameters): MovieResponse? {
        return try {
            retrofit.create<MovieService>().getMoviesOrderByYear(
                location = queryParameters.locationSlug,
                actualTime = queryParameters.actualSince,
                page =queryParameters. page
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

    override suspend fun getMovieShows(queryParameters: QueryParameters): MovieShowResponse? {
        return try {
            retrofit.create<MovieService>().getMovieShowing(
                movieId = queryParameters.id,
                actualSince = queryParameters.actualSince,
                actualUntil = queryParameters.actualUntil,
                page = queryParameters.page,
                location = queryParameters.locationSlug
            )
        } catch (e: Exception) {
            null
        }
    }
}