package com.example.afishaapp.ui.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.Category
import com.example.afishaapp.data.module.City
import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.domain.http.GetEvent
import com.example.afishaapp.domain.http.GetMovie
import com.example.afishaapp.domain.module.EventCategory
import com.example.afishaapp.domain.preferences.SetPreferences
import com.example.afishaapp.domain.repository.PreferencesRepository
import com.example.afishaapp.domain.repository.http.CategoryRepository
import com.example.afishaapp.domain.repository.http.CityRepository
import com.example.afishaapp.ui.screen.bottomSheet.DefaultObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

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
    var locationName by mutableStateOf("")
        private set
    var currentLocationSlug by mutableStateOf("")
        private set

    var events by mutableStateOf<List<Event>>(listOf())
        private set
    var movies by mutableStateOf<List<Movie>>(listOf())
        private set
    var eventsConcert by mutableStateOf<List<Event>>(listOf())
        private set
    var eventsExhibition by mutableStateOf<List<Event>>(listOf())
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
            setPreferences.setDefaultCity(
                city = city.name,
                slug = city.slug
            )
        }
    }

    fun getDefaultCity() {
        viewModelScope.launch {
            preferencesRepository.getDefaultCity().collect {
                locationName = it
            }
        }
    }

    fun getLocationSlug() {
        viewModelScope.launch {
            preferencesRepository.getLocationSlug().collect {
                currentLocationSlug = it
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

    fun getEvents(
        locationSlug: String = currentLocationSlug,
        category: Category = currentCategory
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            val queryParameters = QueryParameters(
                locationSlug = locationSlug,
                category = category.slug
            )

            val response = getEvent.getEvents(queryParameters)

            response?.let {
                events = it.results
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            val queryParameters = QueryParameters(
                locationSlug = locationSlug,
                category = EventCategory.CONCERT.slug
            )

            val response = getEvent.getEvents(queryParameters)

            response?.let {
                eventsConcert = response.results
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            val queryParameters = QueryParameters(
                locationSlug = locationSlug,
                category = EventCategory.EXHIBITION.slug
            )

            val response = getEvent.getEvents(queryParameters)

            response?.let {
                eventsExhibition = response.results
            }
        }
    }

    fun getMovies(locationSlug: String = currentLocationSlug) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getMovie.getMoviesByRating(locationSlug)

            response?.let {
                movies = it.results
            }
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