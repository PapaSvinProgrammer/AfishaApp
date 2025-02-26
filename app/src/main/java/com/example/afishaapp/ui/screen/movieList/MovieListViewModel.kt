package com.example.afishaapp.ui.screen.movieList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.movie.MovieResponse
import com.example.afishaapp.domain.http.GetMovie
import com.example.afishaapp.domain.module.FilterState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val getMovie: GetMovie
): ViewModel() {
    var movieResponse by mutableStateOf<MovieResponse?>(null)
        private set
    var currentFilter by mutableStateOf(FilterState.RATING)
        private set
    var filterStateBottomSheet by mutableStateOf(false)
        private set

    fun getMovie(locationSlug: String, filter: FilterState) {
        when (filter) {
            FilterState.RATING -> getMovieByRating(locationSlug)
            FilterState.YEAR -> getMovieByYear(locationSlug)
            FilterState.TITLE -> getMovieByTitle(locationSlug)
        }
    }

    fun updateCurrentFilter(filter: FilterState) {
        currentFilter = filter
    }

    fun updateFilterStateBottomSheet(state: Boolean) {
        filterStateBottomSheet = state
    }

    private fun getMovieByRating(locationSlug: String) {
        viewModelScope.launch(Dispatchers.IO) {
            movieResponse = getMovie.getMoviesByRating(locationSlug)
        }
    }

    private fun getMovieByTitle(locationSlug: String) {
        viewModelScope.launch(Dispatchers.IO) {
           movieResponse = getMovie.getMoviesByTitle(locationSlug)
        }
    }

    private fun getMovieByYear(locationSlug: String) {
        viewModelScope.launch(Dispatchers.IO) {
            movieResponse = getMovie.getMoviesByYear(locationSlug)
        }
    }
}