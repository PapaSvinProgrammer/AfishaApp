package com.example.afishaapp.domain.repository.http

import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.place.PlaceResponse

interface PlaceRepository {
    suspend fun getPlaces(queryParameters: QueryParameters): PlaceResponse?
    suspend fun getPlaceInfo(placeId: Int): Place?
    suspend fun getPlaceShortInfo(placeId: Int): Place?
    suspend fun getPlacesWithRadius(queryParameters: QueryParameters): PlaceResponse?
}