package com.example.afishaapp.ui.screen.favorite

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.app.utils.SlideState
import com.example.afishaapp.app.utils.convertClass.toEventList
import com.example.afishaapp.app.utils.convertClass.toMovieList
import com.example.afishaapp.app.utils.convertClass.toPlaceList
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.domain.module.EventFilter
import com.example.afishaapp.domain.module.MovieFilter
import com.example.afishaapp.domain.repository.room.LikeEventRepository
import com.example.afishaapp.domain.repository.room.LikeMovieRepository
import com.example.afishaapp.domain.repository.room.LikePlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(
    private val likeEventRepository: LikeEventRepository,
    private val likePlaceRepository: LikePlaceRepository,
    private val likeMovieRepository: LikeMovieRepository
): ViewModel() {
    var indexScreen by mutableIntStateOf(0)
        private set
    var slideState by mutableStateOf(SlideState.LEFT)
        private set

    var filterEvent by mutableStateOf(EventFilter.DEFAULT)
        private set
    var filterMovie by mutableStateOf(MovieFilter.RATING)
        private set
    var filterPlace by mutableStateOf(EventFilter.DEFAULT)
        private set

    var filterEventBottomSheet by mutableStateOf(false)
        private set
    var filterMovieBottomSheet by mutableStateOf(false)
        private set
    var filterPlaceBottomSheet by mutableStateOf(false)
        private set

    var events by mutableStateOf<List<Event>>(listOf())
        private set
    var movies by mutableStateOf<List<Movie>>(listOf())
        private set
    var places by mutableStateOf<List<Place>>(listOf())
        private set

    fun updateEventFilter(filter: EventFilter) {
        filterEvent = filter
    }

    fun updateMovieFilter(filter: MovieFilter) {
        filterMovie = filter
    }

    fun updatePlaceFilter(filter: EventFilter) {
        filterPlace = filter
    }

    fun updateFilterEventBottomSheet(state: Boolean) {
        filterEventBottomSheet = state
    }

    fun updateFilterMovieBottomSheet(state: Boolean) {
        filterMovieBottomSheet = state
    }

    fun updateFilterPlaceBottomSheet(state: Boolean) {
        filterPlaceBottomSheet = state
    }

    fun updateSlideState(state: SlideState) {
        slideState = state
    }

    fun updateIndexScreen(index: Int) {
        indexScreen = index
    }

    fun getEvents() {
        when (filterEvent) {
            EventFilter.DEFAULT -> getEventsByDefault()
            EventFilter.NAME -> getEventsByName()
        }
    }

    fun getMovies() {
        when (filterMovie) {
            MovieFilter.YEAR -> getMoviesByYear()
            MovieFilter.DEFAULT -> getMovieByDefault()
            MovieFilter.RATING -> getMoviesByRating()
        }
    }

    fun getPlaces() {
        when (filterPlace) {
            EventFilter.DEFAULT -> getPlacesByDefault()
            EventFilter.NAME -> getPlacesByName()
        }
    }

    private fun getEventsByName() {
        viewModelScope.launch(Dispatchers.IO) {
            events = likeEventRepository.getByName().toEventList()
        }
    }

    private fun getEventsByDefault() {
        viewModelScope.launch(Dispatchers.IO) {
            events = likeEventRepository.getByDefault().toEventList()
        }
    }

    private fun getMoviesByRating() {
        viewModelScope.launch(Dispatchers.IO) {
            movies = likeMovieRepository.getByRating().toMovieList()
        }
    }

    private fun getMovieByDefault() {
        viewModelScope.launch(Dispatchers.IO) {
            movies = likeMovieRepository.getByDefault().toMovieList()
        }
    }

    private fun getMoviesByYear() {
        viewModelScope.launch(Dispatchers.IO) {
            movies = likeMovieRepository.getByYear().toMovieList()
        }
    }

    private fun getPlacesByName() {
        viewModelScope.launch(Dispatchers.IO) {
            places = likePlaceRepository.getByName().toPlaceList()
        }
    }

    private fun getPlacesByDefault() {
        viewModelScope.launch(Dispatchers.IO) {
            places = likePlaceRepository.getByDefault().toPlaceList()
        }
    }
}