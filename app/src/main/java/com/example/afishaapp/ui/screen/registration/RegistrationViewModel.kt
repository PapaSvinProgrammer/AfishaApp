package com.example.afishaapp.ui.screen.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.User
import com.example.afishaapp.domain.auth.RegistrationFirebase
import com.example.afishaapp.domain.module.AuthData
import com.example.afishaapp.domain.preferences.SetPreferences
import com.example.afishaapp.domain.valid.ValidEmail
import com.example.afishaapp.domain.valid.ValidPassword
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val validEmail: ValidEmail,
    private val validPassword: ValidPassword,
    private val setPreferences: SetPreferences,
    private val registrationFirebase: RegistrationFirebase
): ViewModel() {
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
    var visibilityProgressBar by mutableStateOf(false)
        private set

    var errorPassword by mutableStateOf(false)
        private set
    var errorCheckPassword by mutableStateOf(false)
        private set
    var errorEmail by mutableStateOf(false)
        private set

    var isRegistration by mutableStateOf(false)
        private set
    var errorRegistration by mutableStateOf(false)
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

    fun registration() {
        errorEmail = !validEmail.execute(email)
        errorPassword = !validPassword.execute(password)
        errorCheckPassword = password != checkPassword

        if (errorEmail || errorPassword || errorCheckPassword) {
            return
        }

        visibilityProgressBar = true

        viewModelScope.launch {
            val result = registrationFirebase.execute(
                AuthData(
                    email = email,
                    password = password
                )
            )

            registrationIsSuccess(result)
        }
    }

    private fun registrationIsSuccess(userInfo: User?) {
        if (userInfo == null) {
            password = ""
            checkPassword = ""
            visibilityProgressBar = false
            errorRegistration = true

            return
        }

        viewModelScope.launch {
            setPreferences.setEmail(userInfo.email)
            setPreferences.setUserName(userInfo.name)
            setPreferences.setEntryState(true)

            isRegistration = true
        }
    }
}