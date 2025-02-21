package com.example.afishaapp.data.module.event

data class EventResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Event>
)