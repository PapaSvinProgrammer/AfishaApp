package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.comment.CommentResponse
import com.example.afishaapp.data.module.event.EventResponse
import com.example.afishaapp.data.module.search.SearchResponse

object DefaultResponse {
    val DEFAULT_COMMENT_RESPONSE = CommentResponse(
        count = 0,
        next = "",
        previous = "",
        results = listOf()
    )

    val DEFAULT_SEARCH_RESPONSE = SearchResponse(
        count = 0,
        next = "",
        previous = "",
        results = listOf(),
        ctype = ""
    )

    val DEFAULT_EVENT_RESPONSE = EventResponse(
        count = 0,
        next = "",
        previous = "",
        results = listOf()
    )
}