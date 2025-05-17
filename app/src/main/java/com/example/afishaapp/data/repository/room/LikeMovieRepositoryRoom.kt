package com.example.afishaapp.data.repository.room

import com.example.afishaapp.app.utils.convertClass.toMovieEntity
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.room.likeMovie.MovieDao
import com.example.afishaapp.data.room.likeMovie.MovieEntity
import com.example.afishaapp.domain.repository.room.LikeMovieRepository
import javax.inject.Inject

class LikeMovieRepositoryRoom @Inject constructor(
    private val dao: MovieDao
): LikeMovieRepository {
    override suspend fun insert(movie: Movie) {
        dao.insert(movie.toMovieEntity())
    }

    override suspend fun delete(movieId: Int) {
        dao.delete(movieId)
    }

    override suspend fun getByDefault(): List<MovieEntity> {
        return dao.getByDefault()
    }

    override suspend fun getByRating(): List<MovieEntity> {
        return dao.getByRating()
    }

    override suspend fun getByYear(): List<MovieEntity> {
        return dao.getByYear()
    }

    override suspend fun getMovieById(movieId: Int): MovieEntity? {
        return dao.getMovieById(movieId)
    }

    override suspend fun getCount(): Int {
        return dao.getCount()
    }
}