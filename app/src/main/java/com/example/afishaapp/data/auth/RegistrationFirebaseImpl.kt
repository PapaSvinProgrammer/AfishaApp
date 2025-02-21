package com.example.afishaapp.data.auth

import com.example.afishaapp.data.module.User
import com.example.afishaapp.domain.module.AuthData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RegistrationFirebaseImpl @Inject constructor() {
    suspend fun execute(authData: AuthData): User? {
        val auth = Firebase.auth
        var result: User? = null

        try {
            auth.createUserWithEmailAndPassword(authData.email, authData.password)
                .await()
                .user?.let {
                    result = User(
                        name = it.displayName.toString(),
                        email = it.email.toString(),
                        avatar = it.photoUrl.toString(),
                        uid = it.uid
                    )
                }
        } catch (e: Exception) {
            result = null
        }

        return result
    }
}