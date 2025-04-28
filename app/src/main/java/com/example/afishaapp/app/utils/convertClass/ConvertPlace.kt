package com.example.afishaapp.app.utils.convertClass

import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.data.room.likePlace.PlaceEntity

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