package com.example.afishaapp.ui.screen.start

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class StartViewModel @Inject constructor(): ViewModel() {
    var authSuccess by mutableStateOf(false)
        private set

    fun updateAuthStatus(status: Boolean) {
        authSuccess = status
    }

}