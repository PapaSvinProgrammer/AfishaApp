package com.example.afishaapp.data.room.likePlace

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(placeEntity: PlaceEntity)

    @Query("DELETE FROM like_place WHERE place_id = :placeId")
    suspend fun delete(placeId: Int)

    @Query("SELECT * FROM like_place ORDER BY id DESC")
    suspend fun getByDefault(): List<PlaceEntity>

    @Query("SELECT * FROM like_place ORDER BY name ASC")
    suspend fun getByName(): List<PlaceEntity>

    @Query("SELECT * FROM like_place WHERE place_id = :placeId")
    suspend fun getPlaceById(placeId: Int): PlaceEntity?

    @Query("SELECT COUNT(*) FROM like_place")
    suspend fun getCount(): Int
}