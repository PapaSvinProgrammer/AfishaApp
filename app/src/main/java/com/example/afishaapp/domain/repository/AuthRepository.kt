package com.example.afishaapp.domain.repository

import com.example.afishaapp.data.module.User
import com.example.afishaapp.domain.module.AuthData

interface AuthRepository {
    suspend fun login(authData: AuthData): User?
    suspend fun registration(authData: AuthData): User?
}