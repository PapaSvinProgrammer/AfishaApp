package com.example.afishaapp.ui.screen.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor(): ViewModel() {
    var query by mutableStateOf("")
    var expanded by mutableStateOf(false)
}