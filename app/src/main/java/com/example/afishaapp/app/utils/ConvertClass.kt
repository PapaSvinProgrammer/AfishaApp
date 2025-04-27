package com.example.afishaapp.app.utils

import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.data.room.likeEvent.EventEntity
import com.example.afishaapp.data.room.likeMovie.MovieEntity
import com.example.afishaapp.data.room.likePlace.PlaceEntity
import com.example.afishaapp.data.room.ticket.TicketEntity

fun Event.toTicketEntity(): TicketEntity {
    val currentDate = System.currentTimeMillis()
    val duration = this.dates.last().end - this.dates.last().start

    val ticketEntity = TicketEntity(
        name = this.shortTitle,
        eventId = this.id,
        age = this.ageRestriction,
        address = this.place?.address ?: "",
        subway = this.place?.subway ?: "",
        dateBuy = currentDate,
        dateStart = this.dates.last().start,
        duration = duration,
        price = this.price,
        location = this.place?.location ?: "",
        image = this.images.first().image,
        phone = this.place?.phone ?: "",
        placeId = this.place?.id ?: 0
    )

    return ticketEntity
}

fun Event.toEventEntity(): EventEntity {
    val price = ConvertInfo.convertPrice(this.price)
    val start = this.dates.last().start
    val end = this.dates.last().end

    val eventEntity = EventEntity(
        eventId = this.id,
        name = this.shortTitle,
        price = price,
        dateStart = start,
        dateEnd = end,
        image = this.images.first().thumbnails.highImage
    )

    return eventEntity
}

fun Place.toPlaceEntity(): PlaceEntity {
    val placeEntity = PlaceEntity(
        placeId = this.id,
        name = this.title,
        address = this.address,
        location = this.location,
        subway = this.subway,
        image = this.images?.first()?.thumbnails?.highImage ?: ""
    )

    return placeEntity
}

fun Movie.toMovieEntity(): MovieEntity {
    val movieEntity = MovieEntity(
        movieId = this.id,
        name = this.title,
        year = this.year,
        rating = this.imdbRating,
        image = this.images.first().thumbnails.highImage
    )

    return movieEntity
}