package com.example.afishaapp.data.module

data class QueryParameters(
    val id: Int = 0,
    val locationSlug: String = "",
    val actualSince: Int = 0,
    val actualUntil: Int = 0,
    val page: Int = 1,
    val searchText: String = "",
    val category: String = "",
    val categories: List<String> = listOf()
)