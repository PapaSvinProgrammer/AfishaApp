package com.example.afishaapp.ui.screen.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.domain.module.AppTheme
import com.example.afishaapp.domain.preferences.SetPreferences
import com.example.afishaapp.domain.repository.PreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val setPreferences: SetPreferences,
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    var isDark by mutableStateOf(false)
        private set
    var exitState by mutableStateOf(false)
        private set
    var changeThemeState by mutableStateOf(false)
        private set

    fun updateExitState(state: Boolean) {
        exitState = state
    }

    fun updateChangeThemeState(state: Boolean) {
        changeThemeState = state
    }

    fun setTheme(theme: AppTheme) {
        viewModelScope.launch(Dispatchers.IO) {
            when (theme) {
                AppTheme.LIGHT -> setPreferences.setDarkTheme(false)
                AppTheme.DARK -> setPreferences.setDarkTheme(true)
            }
        }
    }

    fun setEntryState(state: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.setEntryState(state)
        }
    }

    fun getDarkTheme() {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.getDarkTheme().collect {
                isDark = it
            }
        }
    }
}