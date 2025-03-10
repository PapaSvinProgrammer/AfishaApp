package com.example.afishaapp.ui.screen.movieShowBottomSheet


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.movieShow.Show
import com.example.afishaapp.domain.http.GetMovieShow
import com.example.afishaapp.domain.repository.PreferencesRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieShowViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository,
    private val getMovieShow: GetMovieShow
): ViewModel() {
    private var currentLocationSlug by mutableStateOf("")

    var selectTime by mutableIntStateOf(0)
        private set
    var shows by mutableStateOf<List<Show>>(listOf())
        private set

    var dateBottomSheetState by mutableStateOf(false)
        private set

    suspend fun getMovieShows(movieId: Int, time: Long) {
        shows = listOf()

        val queryParameters = QueryParameters(
            id = movieId,
            locationSlug = currentLocationSlug
        )

        shows = getMovieShow.execute(queryParameters, time)
    }

    fun updateSelectTime(selectTime: Int) {
        this.selectTime = selectTime
    }

    fun updateDateBottomSheetState(dateBottomSheetState: Boolean) {
        this.dateBottomSheetState = dateBottomSheetState
    }

    fun getLocationSlug() {
        viewModelScope.launch {
            preferencesRepository.getLocationSlug().collect {
                currentLocationSlug = it
            }
        }
    }
}