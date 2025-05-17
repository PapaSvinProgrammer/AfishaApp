package com.example.afishaapp.app.utils.convertClass

import com.example.afishaapp.app.utils.convertData.ConvertInfo
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.module.image.ImageItem
import com.example.afishaapp.data.module.image.Thumbnail
import com.example.afishaapp.data.module.search.DateRange
import com.example.afishaapp.data.room.likeEvent.EventCategoryCount
import com.example.afishaapp.data.room.likeEvent.EventEntity
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
        image = this.images.first().thumbnails.highImage,
        category = this.categories.firstOrNull() ?: "None"
    )

    return eventEntity
}

fun EventEntity.toEvent(): Event {
    val dateRange = DateRange(
        start = this.dateStart,
        end = this.dateEnd
    )

    val imageItem = ImageItem(
        image = this.image,
        thumbnails = Thumbnail(
            highImage = this.image,
            lowImage = this.image
        )
    )

    val event = Event(
        id = this.eventId,
        title = this.name,
        shortTitle = this.name,
        price = this.price,
        dates = listOf(dateRange),
        images = listOf(imageItem)
    )

    return event
}

fun List<EventEntity>.toEventList(): List<Event> {
    val res = ArrayList<Event>()

    this.forEach {
        res.add(it.toEvent())
    }

    return res
}

fun List<EventCategoryCount>.toCategoryMap(): Map<String, Int> {
    val res = mutableMapOf<String, Int>()

    forEach { item ->
        res[item.category] = item.count
    }

    return res
}