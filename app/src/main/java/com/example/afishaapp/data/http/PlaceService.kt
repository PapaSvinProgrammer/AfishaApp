package com.example.afishaapp.data.http

import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.data.module.place.PlaceResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlaceService {
    @GET("public-api/v1.4/places/")
    suspend fun getPlaces(
        @Query("fields") fields: String = "id,title,address,coords,images,subway,location",
        @Query("text_format") textFormat: String = "text",
        @Query("location") location: String,
        @Query("categories") categories: String = "",
        @Query("page") page: Int = 1
    ): PlaceResponse?

    @GET("public-api/v1.4/places/{placeId}/?expand=images")
    suspend fun getPlaceInfo(
        @Path("placeId") placeId: Int
    ): Place?

    @GET("public-api/v1.4/places/{placeId}/")
    suspend fun getPlaceShortInfo(
        @Path("placeId") placeId: Int,
        @Query("fields") fields: String = "id,title,address,phone,subway,location,images,coords",
        @Query("expand") expand: String = "images"
    ): Place?
}