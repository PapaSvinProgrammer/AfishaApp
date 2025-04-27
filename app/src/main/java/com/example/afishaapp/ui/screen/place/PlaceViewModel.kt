package com.example.afishaapp.ui.screen.place

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.comment.Comment
import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.domain.http.GetCommentPlace
import com.example.afishaapp.domain.http.GetPlace
import com.example.afishaapp.domain.repository.room.LikePlaceRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaceViewModel @Inject constructor(
    private val getPlace: GetPlace,
    private val getCommentPlace: GetCommentPlace,
    private val likePlaceRepository: LikePlaceRepository
): ViewModel() {
    var favoriteState by mutableStateOf(false)
        private set

    var place by mutableStateOf<Place?>(null)
        private set
    var comments by mutableStateOf<List<Comment>>(listOf())
        private set

    fun getPlace(placeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            place = getPlace.getPlaceInfo(placeId)
        }
    }

    fun getComments(placeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getCommentPlace.getDesc(placeId)

            response?.let {
                comments = response.results
            }
        }
    }

    fun updateFavoriteState(state: Boolean) {
        favoriteState = state
    }

    fun findLikePlace() {
        place?.let {
            viewModelScope.launch(Dispatchers.IO) {
                likePlaceRepository.getPlaceById(it.id)
            }
        }
    }

    fun addLikePlace() {
        place?.let {
            viewModelScope.launch(Dispatchers.IO) {
                likePlaceRepository.insert(it)
            }
        }
    }

    fun deleteLikePlace() {
        place?.let {
            viewModelScope.launch(Dispatchers.IO) {
                likePlaceRepository.delete(it.id)
            }
        }
    }
}