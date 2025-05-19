package com.example.afishaapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.domain.repository.PreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    var isDarkTheme by mutableStateOf(false)
        private set
    var isEntry by mutableStateOf(false)
        private set

    fun getTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.getDarkTheme().collect {
                isDarkTheme = it
            }
        }
    }

    fun getEntryState() {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.getEntryState().collect {
                isEntry = it
            }
        }
    }
}