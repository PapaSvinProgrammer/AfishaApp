package com.example.afishaapp.data.repository.room

import com.example.afishaapp.app.utils.convertClass.toPlaceEntity
import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.data.room.likePlace.PlaceDao
import com.example.afishaapp.data.room.likePlace.PlaceEntity
import com.example.afishaapp.domain.repository.room.LikePlaceRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LikePlaceRepositoryRoom @Inject constructor(
    private val dao: PlaceDao
): LikePlaceRepository {
    override suspend fun insert(place: Place) {
        dao.insert(place.toPlaceEntity())
    }

    override suspend fun delete(placeId: Int) {
        dao.delete(placeId)
    }

    override suspend fun getByDefault(): List<PlaceEntity> {
        return dao.getByDefault()
    }

    override suspend fun getByName(): List<PlaceEntity> {
        return dao.getByName()
    }

    override suspend fun getPlaceById(placeId: Int): PlaceEntity? {
        return dao.getPlaceById(placeId)
    }

    override fun getCount(): Flow<Int> {
        return dao.getCount()
    }
}