package com.example.afishaapp.app.utils.convertClass

import com.example.afishaapp.data.module.search.DateRange
import com.example.afishaapp.data.module.search.ResultItem
import com.example.afishaapp.data.room.ticket.TicketEntity

private const val CTYPE = "event"

fun TicketEntity.toResultItem(): ResultItem {
    val dateRange = DateRange(
        start = dateStart,
        end = dateStart + duration
    )

    return ResultItem(
        id = eventId,
        title = name,
        ctype = CTYPE,
        favoriteCount = 0,
        commentsCount = 0,
        description = "",
        address = address,
        dateRange = dateRange
    )
}

fun List<TicketEntity>.toResultItemList(): List<ResultItem> {
    val res = mutableListOf<ResultItem>()

    this.forEach {
        res.add(it.toResultItem())
    }

    return res
}