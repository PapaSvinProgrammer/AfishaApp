package com.example.afishaapp.ui.screen.movieList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.module.movie.MovieResponse
import com.example.afishaapp.domain.http.GetMovie
import com.example.afishaapp.domain.module.BaseFilterType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val getMovie: GetMovie
): ViewModel() {
    private var nextMoviesPage = 2
    private var locationSlug = ""

    var movies by mutableStateOf<List<Movie>>(listOf())
        private set

    var currentFilter by mutableStateOf(BaseFilterType.RATING)
        private set
    var filterStateBottomSheet by mutableStateOf(false)
        private set

    fun getMovies(
        filter: BaseFilterType = currentFilter
    ) {
        movies = listOf()

        when (filter) {
            BaseFilterType.RATING -> getMovieByRating()
            BaseFilterType.YEAR -> getMovieByYear()
            BaseFilterType.TITLE -> getMovieByTitle()
        }
    }

    fun updateCurrentFilter(filter: BaseFilterType) {
        currentFilter = filter
    }

    fun updateFilterStateBottomSheet(state: Boolean) {
        filterStateBottomSheet = state
    }

    fun loadMoreMovies() {
        when (currentFilter) {
            BaseFilterType.RATING -> loadMoreByRating()
            BaseFilterType.YEAR -> loadMoreByYear()
            BaseFilterType.TITLE -> loadMoreByTitle()
        }
    }

    fun updateLocationSlug(slug: String) {
        locationSlug = slug
    }

    private fun loadMoreByRating() {
        viewModelScope.launch(Dispatchers.IO) {
            val movieResponse = getMovie.getMoviesByRating(
                locationSlug = locationSlug,
                page = nextMoviesPage
            )

            addMoviesList(movieResponse)
        }
    }

    private fun loadMoreByTitle() {
        viewModelScope.launch(Dispatchers.IO) {
            val movieResponse = getMovie.getMoviesByTitle(
                locationSlug = locationSlug,
                page = nextMoviesPage
            )

            addMoviesList(movieResponse)
        }
    }

    private fun loadMoreByYear() {
        viewModelScope.launch(Dispatchers.IO) {
            val movieResponse = getMovie.getMoviesByYear(
                locationSlug = locationSlug,
                page = nextMoviesPage
            )

            addMoviesList(movieResponse)
        }
    }

    private fun getMovieByRating() {
        viewModelScope.launch(Dispatchers.IO) {
            val movieResponse = getMovie.getMoviesByRating(locationSlug)

            setMoviesList(movieResponse)
        }
    }

    private fun getMovieByTitle() {
        viewModelScope.launch(Dispatchers.IO) {
            val movieResponse = getMovie.getMoviesByTitle(locationSlug)

            setMoviesList(movieResponse)
        }
    }

    private fun getMovieByYear() {
        viewModelScope.launch(Dispatchers.IO) {
            val movieResponse = getMovie.getMoviesByYear(locationSlug)

            setMoviesList(movieResponse)
        }
    }

    private fun setMoviesList(movieResponse: MovieResponse?) {
        movieResponse?.let {
            movies = it.results
        }
    }

    private fun addMoviesList(movieResponse: MovieResponse?) {
        movieResponse?.let {
            val temp = movies.toMutableList()
            temp.addAll(movieResponse.results)

            movies = temp
            nextMoviesPage += 1
        }
    }
}