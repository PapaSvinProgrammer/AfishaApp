package com.example.afishaapp.ui.screen.movie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.data.module.movieShow.Show
import com.example.afishaapp.domain.http.GetMovie
import com.example.afishaapp.domain.http.GetMovieShow
import com.example.afishaapp.domain.repository.PreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMovieShow: GetMovieShow,
    private val getMovie: GetMovie,
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    var favoriteState by mutableStateOf(false)
        private set
    var movie by mutableStateOf<Movie?>(null)
        private set
    var currentLocationSlug by mutableStateOf("")
        private set

    var shows by mutableStateOf<List<Show>>(listOf())
        private set
    var showsBottomState by mutableStateOf(false)
        private set

    fun updateFavoriteState(favoriteState: Boolean) {
        this.favoriteState = favoriteState
    }

    fun getMovie(movieId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movie = getMovie.getMovieInfo(movieId)
        }
    }

    fun getMovieShows(movieId: Int, locationSlug: String) {
        if (shows.isNotEmpty()) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val queryParameters = QueryParameters(
                id = movieId,
                locationSlug = locationSlug
            )

            shows = getMovieShow.execute(queryParameters, 0)
        }
    }

    fun getLocationSlug() {
        viewModelScope.launch {
            preferencesRepository.getLocationSlug().collect {
                currentLocationSlug = it
            }
        }
    }

    fun updateShowsBottomState(showsBottomState: Boolean) {
        this.showsBottomState = showsBottomState
    }
}