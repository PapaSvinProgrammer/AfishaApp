package com.example.afishaapp.data.repository.http

import com.example.afishaapp.data.http.PlaceService
import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.data.module.place.PlaceResponse
import com.example.afishaapp.domain.repository.http.PlaceRepository
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class PlaceRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
): PlaceRepository {
    override suspend fun getPlaces(queryParameters: QueryParameters): PlaceResponse? {
        return try {
            retrofit.create<PlaceService>().getPlaces(
                location = queryParameters.locationSlug,
                page = queryParameters.page,
                categories = queryParameters.categories.joinToString(",")
            )
        }
        catch (e: Exception) {
            null
        }
    }

    override suspend fun getPlaceInfo(placeId: Int): Place? {
        return try {
            retrofit.create<PlaceService>().getPlaceInfo(placeId)
        }
        catch (e: Exception) {
            null
        }
    }

    override suspend fun getPlaceShortInfo(placeId: Int): Place? {
        return try {
            retrofit.create<PlaceService>().getPlaceShortInfo(placeId)
        }
        catch (e: Exception) {
            null
        }
    }

    override suspend fun getPlacesWithRadius(queryParameters: QueryParameters): PlaceResponse? {
        return try {
            retrofit.create<PlaceService>().getPlacesWithRadius(
                categories = queryParameters.categories.joinToString(","),
                page = queryParameters.page,
                lat = queryParameters.lat,
                lon = queryParameters.lon,
                radius = queryParameters.radius
            )
        }
        catch(e: Exception) {
            null
        }
    }
}