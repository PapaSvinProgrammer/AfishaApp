package com.example.afishaapp.app.navigation

import kotlinx.serialization.Serializable

@Serializable
object StartRoute

@Serializable
object EntryRoute

@Serializable
object RegistrationRoute

@Serializable
object HomeRoute

@Serializable
object TicketsRoute

@Serializable
object FavoriteRoute

@Serializable
object SearchRoute

@Serializable
object AccountRoute

@Serializable
data class MovieListRoute(
    val title: String,
    val location: String
)

@Serializable
data class EventListRoute(
    val title: String,
    val categorySlug: String,
    val location: String
)

@Serializable
data class EventRoute(val eventId: Int)

@Serializable
data class CommentListRoute(
    val id: Int,
    val name: String,
    val type: String
)

@Serializable
data class MovieRoute(
    val id: Int
)