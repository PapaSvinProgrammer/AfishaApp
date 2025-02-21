package com.example.afishaapp.ui.screen.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.domain.http.GetEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getEvent: GetEvent
): ViewModel() {
    val cityBottomSheetState = mutableStateOf(false)

    fun search(text: String) {
        viewModelScope.launch(Dispatchers.IO) {

        }
    }
}