package com.example.afishaapp.data.repository.room

import com.example.afishaapp.data.room.likePlace.PlaceDao
import com.example.afishaapp.data.room.likePlace.PlaceEntity
import com.example.afishaapp.domain.repository.room.LikePlaceRepository
import javax.inject.Inject

class LikePlaceRepositoryRoom @Inject constructor(
    private val dao: PlaceDao
): LikePlaceRepository {
    override suspend fun insert(placeEntity: PlaceEntity) {
        dao.insert(placeEntity)
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

    override suspend fun search(q: String): List<PlaceEntity> {
        TODO("Not yet implemented")
    }
}