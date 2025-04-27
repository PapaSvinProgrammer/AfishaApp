package com.example.afishaapp.ui.screen.movie

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.app.utils.ParseHtml
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.domain.http.GetMovie
import com.example.afishaapp.domain.repository.room.LikeMovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieViewModel @Inject constructor(
    private val getMovie: GetMovie,
    private val likeMovieRepository: LikeMovieRepository
): ViewModel() {
    var favoriteState by mutableStateOf(false)
        private set
    var movie by mutableStateOf<Movie?>(null)
        private set

    var showsBottomState by mutableStateOf(false)
        private set
    var parseMovieDescription by mutableStateOf("")
        private set
    var parseMovieBodyText by mutableStateOf("")
        private set

    fun parseInfo(
        item: Movie? = movie
    ) {
        item?.let {
            viewModelScope.launch {
                parseMovieDescription = ParseHtml.getText(it.description)
                parseMovieBodyText = ParseHtml.getText(it.bodyText)
            }
        }
    }

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

    fun addLikeMovie() {
        movie?.let {
            viewModelScope.launch(Dispatchers.IO) {
                likeMovieRepository.insert(it)
            }
        }
    }

    fun deleteLikeMovie() {
        movie?.let {
            viewModelScope.launch(Dispatchers.IO) {
                likeMovieRepository.delete(it.id)
            }
        }
    }

    fun findLikeMovie() {
        movie?.let {
            viewModelScope.launch(Dispatchers.IO) {
                val res = likeMovieRepository.getMovieById(it.id)
                favoriteState = res != null
            }
        }
    }
}