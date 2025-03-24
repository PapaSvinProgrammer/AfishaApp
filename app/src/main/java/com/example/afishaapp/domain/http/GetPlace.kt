package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.data.module.place.PlaceResponse
import com.example.afishaapp.domain.repository.http.PlaceRepository
import javax.inject.Inject

class GetPlace @Inject constructor(
    private val placeRepository: PlaceRepository
) {
    suspend fun getPlaces(queryParameters: QueryParameters): PlaceResponse? {
        if (queryParameters.page <= 0 || queryParameters.locationSlug.isEmpty()) {
            return null
        }

        return placeRepository.getPlaces(queryParameters)
    }

    suspend fun getPlaceInfo(placeId: Int): Place? {
        if (placeId <= 0) {
            return null
        }

        return placeRepository.getPlaceInfo(placeId)
    }

    suspend fun getPlaceShortInfo(placeId: Int): Place? {
        if (placeId <= 0) {
            return null
        }

        return placeRepository.getPlaceShortInfo(placeId)
    }
}