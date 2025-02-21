package com.example.afishaapp.data.module.comment

data class CommentResponse(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<Comment>
)