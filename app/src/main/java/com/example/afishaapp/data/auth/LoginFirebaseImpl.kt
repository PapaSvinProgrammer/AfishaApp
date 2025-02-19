package com.example.afishaapp.data.auth

import com.example.afishaapp.data.module.UserInfo
import com.example.afishaapp.domain.module.AuthData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginFirebaseImpl @Inject constructor() {
    suspend fun execute(authData: AuthData): UserInfo? {
        val firebase = Firebase.auth
        var result: UserInfo? = null

        try {
            firebase
                .signInWithEmailAndPassword(authData.email, authData.password)
                .await()
                .user?.let {
                    result = UserInfo(
                        name = it.displayName.toString(),
                        email = it.email.toString(),
                        imageUrl = it.photoUrl.toString(),
                        uid = it.uid
                    )
                }
        } catch (e: Exception) {
            return null
        }

        return result
    }
}