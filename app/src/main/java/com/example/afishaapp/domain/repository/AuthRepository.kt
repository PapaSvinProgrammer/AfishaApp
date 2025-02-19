package com.example.afishaapp.domain.repository

import com.example.afishaapp.data.module.UserInfo
import com.example.afishaapp.domain.module.AuthData

interface AuthRepository {
    suspend fun login(authData: AuthData): UserInfo?
    suspend fun registration(authData: AuthData): UserInfo?
}