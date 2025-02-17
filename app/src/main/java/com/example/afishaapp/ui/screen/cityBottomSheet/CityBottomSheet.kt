package com.example.afishaapp.ui.screen.cityBottomSheet

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityBottomSheet(
    visibilityState: MutableState<Boolean>
) {
    if (visibilityState.value) {
        ModalBottomSheet(
            onDismissRequest = {
                visibilityState.value = false
            },
            sheetState = rememberModalBottomSheetState()
        ) {
            Text(text = "BOTTOM SHEET")
        }
    }
}