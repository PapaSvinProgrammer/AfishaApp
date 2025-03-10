package com.example.afishaapp.data.http

import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.module.event.EventResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EventService {
    @GET("public-api/v1.4/events/")
    suspend fun getEvents(
        @Query("actual_since") currentTime: Int,
        @Query("location") location: String,
        @Query("categories") categories: String,
        @Query("page") page: Int = 1,
        @Query("fields") fields: String = "id,title,place,price,dates,images,short_title",
        @Query("expand") expand: String = "place,images"
    ): EventResponse

    @GET("public-api/v1.4/events/{eventId}/?expand=place,images")
    suspend fun getEventInfo(@Path("eventId") eventId: Int): Event
}