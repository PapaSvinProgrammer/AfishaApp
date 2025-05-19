package com.example.afishaapp.app.navigation

import kotlinx.serialization.Serializable

sealed class NavRoute

@Serializable
data object StartRoute: NavRoute()

@Serializable
data object EntryRoute: NavRoute()

@Serializable
data object RegistrationRoute: NavRoute()

@Serializable
data object HomeRoute: NavRoute()

@Serializable
data object TicketsRoute: NavRoute()

@Serializable
data object FavoriteRoute: NavRoute()

@Serializable
data object SearchRoute: NavRoute()

@Serializable
data object ProfileRoute: NavRoute()

@Serializable
data class MovieListRoute(
    val title: String,
    val location: String
): NavRoute()

@Serializable
data class EventListRoute(
    val title: String,
    val categorySlug: String,
    val location: String
): NavRoute()

@Serializable
data class EventRoute(val eventId: Int): NavRoute()

@Serializable
data class CommentListEventRoute(
    val id: Int,
    val name: String
): NavRoute()

@Serializable
data class CommentListPlaceRoute(
    val id: Int,
    val name: String
): NavRoute()

@Serializable
data class MovieRoute(
    val id: Int
): NavRoute()

@Serializable
data class AboutEventRoute(
    val id: Int
): NavRoute()

@Serializable
data class AboutMovieRoute(
    val id: Int
): NavRoute()

@Serializable
data object AboutAppRoute: NavRoute()

@Serializable
data class MapRoute(
    val placeId: Int,
    val lat: Double,
    val lon: Double
): NavRoute()

@Serializable
data class PlaceRoute(
    val placeId: Int
): NavRoute()

@Serializable
data class FormPaymentRoute(
    val eventId: Int
): NavRoute()

@Serializable
data class DetailTicketRoute(
    val eventId: Int
): NavRoute()

@Serializable
data object StartSettingRoute: NavRoute()

@Serializable
data object ChartRoute: NavRoute()