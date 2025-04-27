package com.example.afishaapp.data.room.likeMovie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "like_movie",
    indices = [
        Index(
            value = ["movie_id"],
            unique = true
        )
    ]
)
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "movie_id") val movieId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "rating") val rating: Float,
    @ColumnInfo(name = "image") val image: String
)