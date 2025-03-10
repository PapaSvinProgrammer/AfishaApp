package com.example.afishaapp.ui.screen.movie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.domain.http.GetMovie
import com.example.afishaapp.domain.repository.PreferencesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMovie: GetMovie
): ViewModel() {
    var favoriteState by mutableStateOf(false)
        private set
    var movie by mutableStateOf<Movie?>(null)
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

    fun updateShowsBottomState(showsBottomState: Boolean) {
        this.showsBottomState = showsBottomState
    }
}