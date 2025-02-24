package com.example.afishaapp.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.City
import com.example.afishaapp.data.module.event.EventResponse
import com.example.afishaapp.data.module.movieShow.MovieShowResponse
import com.example.afishaapp.domain.http.GetEvent
import com.example.afishaapp.domain.http.GetMovieShow
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
    private val getEvent: GetEvent,
    private val getMovieShow: GetMovieShow
): ViewModel() {
    var cityBottomSheetState by mutableStateOf(false)
        private set
    var city by mutableStateOf<List<City>>(listOf())
        private set
    var defaultCity by mutableStateOf("")
        private set
    var eventResponse by mutableStateOf<EventResponse?>(null)
        private set
    var movieShowResponse by mutableStateOf<MovieShowResponse?>(null)
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
        if (eventResponse != null) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            eventResponse = getEvent.getEvents()
        }
    }

    fun getMovieShows() {
        if (movieShowResponse != null) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            movieShowResponse = getMovieShow.getMoviesShow()
        }
    }
}