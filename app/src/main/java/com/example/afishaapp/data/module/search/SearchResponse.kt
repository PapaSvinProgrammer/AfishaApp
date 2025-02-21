package com.example.afishaapp.data.module.search

data class SearchResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<ResultItem>,
    val ctype: String
)