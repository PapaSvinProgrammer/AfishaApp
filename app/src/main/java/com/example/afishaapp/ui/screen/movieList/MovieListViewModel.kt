package com.example.afishaapp.ui.screen.movieList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.movieShow.MovieShowResponse
import com.example.afishaapp.domain.http.GetEvent
import com.example.afishaapp.domain.http.GetMovieShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val getMovieShow: GetMovieShow
): ViewModel() {
    var movieShowResponse by mutableStateOf<MovieShowResponse?>(null)
        private set

    fun getMovieShows(locationSlug: String) {
        viewModelScope.launch(Dispatchers.IO) {
            movieShowResponse = getMovieShow.getMoviesShow(locationSlug)
        }
    }
}