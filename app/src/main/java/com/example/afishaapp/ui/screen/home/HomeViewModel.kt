package com.example.afishaapp.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.City
import com.example.afishaapp.domain.preferences.SetPreferences
import com.example.afishaapp.domain.repository.PreferencesRepository
import com.example.afishaapp.domain.repository.http.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val preferencesRepository: PreferencesRepository,
    private val setPreferences: SetPreferences
): ViewModel() {
    val cityBottomSheetState = mutableStateOf(false)
    val city = mutableStateOf<List<City>>(listOf())
    var defaultCity by mutableStateOf("")
        private set

    fun getCity() {
        if (city.value.isNotEmpty()) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            city.value = cityRepository.getCities()
        }
    }

    fun updateDefaultCity(city: City) {
        viewModelScope.launch {
            setPreferences.setDefaultCity(city.name)
        }
    }

    fun getDefaultCity() {
        viewModelScope.launch {
            preferencesRepository.getDefaultCity().collect {
                defaultCity = it
            }
        }
    }
}