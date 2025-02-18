package com.example.afishaapp.ui.screen.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(): ViewModel() {
    var email by mutableStateOf("")
        private set
    var password by mutableStateOf("")
        private set
    var checkPassword by mutableStateOf("")
        private set

    var visibilityPassword by mutableStateOf(false)
        private set
    var visibilityCheckPassword by mutableStateOf(false)
        private set

    var errorPassword by mutableStateOf(false)
        private set
    var errorCheckPassword by mutableStateOf(false)
        private set
    var errorEmail by mutableStateOf(false)
        private set

    fun updateEmail(text: String) {
        email = text
    }

    fun updatePassword(text: String) {
        password = text
    }

    fun updateCheckPassword(text: String) {
        checkPassword = text
    }

    fun updateVisibilityPassword(state: Boolean) {
        visibilityPassword = state
    }

    fun updateVisibilityCheckPassword(state: Boolean) {
        visibilityCheckPassword = state
    }

    fun updateErrorPassword(state: Boolean) {
        errorPassword = state
    }

    fun updateErrorCheckPassword(state: Boolean) {
        errorCheckPassword = state
    }

    fun updateErrorEmail(state: Boolean) {
        errorEmail = state
    }
}