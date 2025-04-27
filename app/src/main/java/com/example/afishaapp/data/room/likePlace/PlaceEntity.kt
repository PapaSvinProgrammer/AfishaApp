package com.example.afishaapp.data.room.likePlace

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "like_place",
    indices = [
        Index(
            value = ["place_id"],
            unique = true
        )
    ]
)
data class PlaceEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "place_id") val placeId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "location") val location: String,
    @ColumnInfo(name = "subway") val subway: String,
    @ColumnInfo(name = "image") val image: String
)