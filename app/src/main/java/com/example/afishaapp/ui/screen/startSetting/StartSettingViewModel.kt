package com.example.afishaapp.ui.screen.startSetting

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.app.utils.CategoryNode
import com.example.afishaapp.data.module.City
import com.example.afishaapp.domain.repository.PreferencesRepository
import com.example.afishaapp.domain.repository.http.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class StartSettingViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    var cities by mutableStateOf<List<City>>(listOf())
        private set

    var selectedCity by mutableStateOf("")
        private set
    var selectedDate by mutableStateOf("DD/MM/YYYY")
        private set
    val selectedCategory = mutableStateMapOf<String, Boolean>()

    fun updateSelectedCity(selectedCity: String) {
        this.selectedCity = selectedCity
    }

    fun updateSelectedDate(selectedDate: String) {
        this.selectedDate = selectedDate
    }

    fun getCities() {
        viewModelScope.launch(Dispatchers.IO) {
            cities = cityRepository.getCities()
        }
    }

    fun saveCity(city: City) {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.setDefaultCity(city.name)
            preferencesRepository.setLocationSlug(city.slug)
        }
    }
}