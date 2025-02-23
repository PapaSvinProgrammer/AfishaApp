package com.example.afishaapp.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.City
import com.example.afishaapp.data.module.event.EventResponse
import com.example.afishaapp.domain.http.DefaultResponse
import com.example.afishaapp.domain.http.DefaultResponse.DEFAULT_EVENT_RESPONSE
import com.example.afishaapp.domain.http.GetEvent
import com.example.afishaapp.domain.preferences.SetPreferences
import com.example.afishaapp.domain.repository.PreferencesRepository
import com.example.afishaapp.domain.repository.http.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val preferencesRepository: PreferencesRepository,
    private val setPreferences: SetPreferences,
    private val getEvent: GetEvent
): ViewModel() {
    var cityBottomSheetState by mutableStateOf(false)
        private set
    var city by mutableStateOf<List<City>>(listOf())
        private set
    var defaultCity by mutableStateOf("")
        private set
    var eventResponse by mutableStateOf(DEFAULT_EVENT_RESPONSE)
        private set

    fun getCity() {
        if (city.isNotEmpty()) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            city = cityRepository.getCities()
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

    fun updateCityState(state: Boolean) {
        cityBottomSheetState = state
    }

    fun getEvents() {
        if (eventResponse != DEFAULT_EVENT_RESPONSE) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            eventResponse = getEvent.getEvents()
        }
    }
}