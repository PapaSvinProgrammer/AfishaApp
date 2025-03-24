package com.example.afishaapp.ui.screen.map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.domain.http.GetPlace
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MapViewModel @Inject constructor(
    private val getPlace: GetPlace
): ViewModel() {
    var place by mutableStateOf<Place?>(null)
        private set

    fun getPlaceInfo(placeId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            place = getPlace.getPlaceShortInfo(placeId)
        }
    }
}