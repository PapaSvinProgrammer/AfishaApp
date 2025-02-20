package com.example.afishaapp.data.module.eventDay

import com.google.gson.annotations.SerializedName

data class EventDay(
    val date: String,
    val location: String,
    @SerializedName("object")
    val eventDayObject: EventDayObject
)