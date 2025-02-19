package com.example.afishaapp.domain.auth

import com.example.afishaapp.data.module.UserInfo
import com.example.afishaapp.domain.module.AuthData
import com.example.afishaapp.domain.repository.AuthRepository
import javax.inject.Inject

class LoginFirebase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun execute(authData: AuthData): UserInfo? {
        if (authData.email.isEmpty() || authData.password.isEmpty()) {
            return null
        }

        return authRepository.login(authData)
    }
}