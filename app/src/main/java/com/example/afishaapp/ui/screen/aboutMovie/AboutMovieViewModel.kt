package com.example.afishaapp.ui.screen.aboutMovie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.app.utils.ParseHtml
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.domain.http.GetMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AboutMovieViewModel @Inject constructor(
    private val getMovie: GetMovie
): ViewModel() {
    var movie by mutableStateOf<Movie?>(null)
        private set

    var parseDescription by mutableStateOf("")
        private set
    var parseBodyText by mutableStateOf("")
        private set

    fun getMovie(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            movie = getMovie.getMovieInfo(id)
        }
    }

    fun parseInfo(
        movie: Movie? = this.movie
    ) {
        movie?.let {
            viewModelScope.launch {
                parseDescription = ParseHtml.getText(it.description)
                parseBodyText = ParseHtml.getText(it.bodyText)
            }
        }
    }
}