package com.example.afishaapp.data.module.eventDay

data class EventDayResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<EventDay>
)