package com.example.afishaapp.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.Category
import com.example.afishaapp.data.module.City
import com.example.afishaapp.data.module.event.EventResponse
import com.example.afishaapp.data.module.movie.MovieResponse
import com.example.afishaapp.domain.http.GetEvent
import com.example.afishaapp.domain.http.GetMovie
import com.example.afishaapp.domain.preferences.SetPreferences
import com.example.afishaapp.domain.repository.PreferencesRepository
import com.example.afishaapp.domain.repository.http.CategoryRepository
import com.example.afishaapp.domain.repository.http.CityRepository
import com.example.afishaapp.ui.screen.bottomSheet.DefaultObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val CONCERT_CATEGORY = "concert"
private const val EXHIBITION_CATEGORY = "exhibition"

class HomeViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val preferencesRepository: PreferencesRepository,
    private val categoryRepository: CategoryRepository,
    private val setPreferences: SetPreferences,
    private val getEvent: GetEvent,
    private val getMovie: GetMovie
): ViewModel() {
    var cityBottomSheetState by mutableStateOf(false)
        private set
    var categoryBottomSheetState by mutableStateOf(false)
        private set

    var city by mutableStateOf<List<City>>(listOf())
        private set
    var defaultCity by mutableStateOf("")
        private set

    var eventResponse by mutableStateOf<EventResponse?>(null)
        private set
    var movieResponse by mutableStateOf<MovieResponse?>(null)
        private set
    var eventConcert by mutableStateOf<EventResponse?>(null)
        private set
    var eventExhibition by mutableStateOf<EventResponse?>(null)
        private set
    var categoryEvent by mutableStateOf<List<Category>>(listOf())
        private set
    var currentCategory by mutableStateOf(DefaultObject.DEFAULT_CATEGORY)
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

    fun updateCategoryState(state: Boolean) {
        categoryBottomSheetState = state
    }

    fun updateCurrentCategory(category: Category) {
        currentCategory = category
    }

    fun getEvents(cityName: String, category: Category) {
        val city = city.filter { it.name == cityName }

        if (city.isEmpty()) return

        viewModelScope.launch(Dispatchers.IO) {
            eventResponse = getEvent.getEvents(
                location = city.first().slug,
                category = category.slug
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            eventConcert = getEvent.getEvents(
                location = city.first().slug,
                category = CONCERT_CATEGORY
            )
        }

        viewModelScope.launch(Dispatchers.IO) {
            eventExhibition = getEvent.getEvents(
                location = city.first().slug,
                category = EXHIBITION_CATEGORY
            )
        }
    }

    fun getMovies(cityName: String) {
        val city = city.filter { it.name == cityName }

        if (city.isEmpty()) return

        viewModelScope.launch(Dispatchers.IO) {
            movieResponse = getMovie.getMoviesByRating(city.first().slug)
        }
    }

    fun getCategories() {
        if (categoryEvent.isNotEmpty()) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val temp = arrayListOf(DefaultObject.DEFAULT_CATEGORY)
            temp.addAll(categoryRepository.getCategoriesEvent())

            categoryEvent = temp
        }
    }
}