package com.example.afishaapp.app.utils.convertClass

import com.example.afishaapp.data.module.image.ImageItem
import com.example.afishaapp.data.module.image.Thumbnail
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

fun PlaceEntity.toPlace(): Place {
    val imageItem = ImageItem(
        image = this.image,
        thumbnails = Thumbnail(
            highImage = this.image,
            lowImage = this.image
        )
    )

    val place = Place(
        id = this.placeId,
        title = this.name,
        address = this.address,
        location = this.location,
        subway = this.subway,
        images = listOf(imageItem)
    )

    return place
}

fun List<PlaceEntity>.toPlaceList(): List<Place> {
    val res = ArrayList<Place>()

    this.forEach {
        res.add(it.toPlace())
    }

    return res
}