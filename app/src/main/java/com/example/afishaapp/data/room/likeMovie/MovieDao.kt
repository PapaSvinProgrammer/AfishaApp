package com.example.afishaapp.data.room.likeMovie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movieEntity: MovieEntity)

    @Query("DELETE FROM like_movie WHERE movie_id = :movieId")
    suspend fun delete(movieId: Int)

    @Query("SELECT * FROM like_movie ORDER BY rating DESC")
    suspend fun getByRating(): List<MovieEntity>

    @Query("SELECT * FROM like_movie ORDER BY id DESC")
    suspend fun getByDefault(): List<MovieEntity>

    @Query("SELECT * FROM like_movie ORDER BY year DESC")
    suspend fun getByYear(): List<MovieEntity>
}