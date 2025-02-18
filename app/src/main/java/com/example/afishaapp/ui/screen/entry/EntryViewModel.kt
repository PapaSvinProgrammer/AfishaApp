package com.example.afishaapp.ui.screen.entry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.domain.valid.ValidEmail
import com.example.afishaapp.domain.valid.ValidPassword
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class EntryViewModel @Inject constructor(
    private val validEmail: ValidEmail,
    private val validPassword: ValidPassword
): ViewModel() {
    var emailValue by mutableStateOf("")
        private set
    var passwordValue by mutableStateOf("")
        private set
    var errorEmail by mutableStateOf(false)
        private set
    var errorPassword by mutableStateOf(false)
        private set
    var visibilityPassword by mutableStateOf(false)
        private set
    var visibilityProgressBar by mutableStateOf(false)
        private set

    var isEntry by mutableStateOf(false)
        private set

    fun updateEmail(text: String) {
        emailValue = text
    }

    fun updatePassword(text: String) {
        passwordValue = text
    }

    fun updateVisibilityPassword(state: Boolean) {
        visibilityPassword = state
    }

    fun entry() {
        errorEmail = !validEmail.execute(emailValue)
        errorPassword = !validPassword.execute(passwordValue)

        if (errorEmail || errorPassword) {
            return
        }

        visibilityProgressBar = true
    }
}