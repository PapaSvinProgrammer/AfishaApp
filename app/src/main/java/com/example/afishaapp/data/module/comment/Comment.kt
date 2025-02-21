package com.example.afishaapp.data.module.comment

import com.example.afishaapp.data.module.User

data class Comment(
    val id: Int,
    val datePosted: Int,
    val text: String,
    val user: User
)