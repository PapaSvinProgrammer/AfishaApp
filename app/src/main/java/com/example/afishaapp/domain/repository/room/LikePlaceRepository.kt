package com.example.afishaapp.domain.repository.room

import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.data.room.likePlace.PlaceEntity

interface LikePlaceRepository {
    suspend fun insert(place: Place)
    suspend fun delete(placeId: Int)
    suspend fun getByDefault(): List<PlaceEntity>
    suspend fun getByName(): List<PlaceEntity>
    suspend fun getPlaceById(placeId: Int): PlaceEntity?
    suspend fun search(q: String): List<PlaceEntity>
}