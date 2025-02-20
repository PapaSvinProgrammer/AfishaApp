package com.example.afishaapp.ui.screen.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.domain.http.GetAgent
import com.example.afishaapp.domain.http.GetCategory
import com.example.afishaapp.domain.http.GetEventsDay
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getCategory: GetCategory,
    private val getEventsDay: GetEventsDay,
    private val getAgent: GetAgent
): ViewModel() {
    val cityBottomSheetState = mutableStateOf(false)

    fun getCategoriesEvent() {
        viewModelScope.launch {

        }
    }

    fun getEventDay() {
        viewModelScope.launch {
            getEventsDay.execute()
        }
    }

    fun getAgent() {

    }
}