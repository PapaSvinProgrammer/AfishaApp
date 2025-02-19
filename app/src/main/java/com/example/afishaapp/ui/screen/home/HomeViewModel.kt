package com.example.afishaapp.ui.screen.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.domain.category.GetCategories
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getCategories: GetCategories
): ViewModel() {
    val cityBottomSheetState = mutableStateOf(false)

    fun getCategoriesEvent() {
        viewModelScope.launch {

        }
    }
}