package com.example.afishaapp.ui.screen.cityBottomSheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.data.module.City

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityBottomSheet(
    visibilityState: MutableState<Boolean>,
    data: MutableState<List<City>>,
    onClick: (city: City) -> Unit
) {
    if (visibilityState.value) {
        ModalBottomSheet(
            onDismissRequest = {
                visibilityState.value = false
            },
            sheetState = rememberModalBottomSheetState()
        ) {
            LazyColumn {
                items(data.value) { city ->
                    Row(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .clickable {
                                onClick.invoke(city)
                            }
                    ) {
                        Text(
                            modifier = Modifier.padding(15.dp),
                            text = city.name,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}