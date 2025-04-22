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
object ProfileRoute

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
data class CommentListEventRoute(
    val id: Int,
    val name: String
)

@Serializable
data class CommentListPlaceRoute(
    val id: Int,
    val name: String
)

@Serializable
data class MovieRoute(
    val id: Int
)

@Serializable
data class AboutEventRoute(
    val id: Int
)

@Serializable
data class AboutMovieRoute(
    val id: Int
)

@Serializable
object AboutAppRoute

@Serializable
data class MapRoute(
    val placeId: Int,
    val lat: Double,
    val lon: Double
)

@Serializable
data class PlaceRoute(
    val placeId: Int
)

@Serializable
object FormPaymentRoute