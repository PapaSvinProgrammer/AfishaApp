package com.example.afishaapp.ui.screen.bottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.afishaapp.R
import com.example.afishaapp.data.module.City
import com.example.afishaapp.ui.widget.text.TitleBottomSheet
import com.example.afishaapp.ui.widget.row.CheckRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityBottomSheet(
    data: List<City>,
    currentCity: String,
    onClick: (city: City) -> Unit,
    dismissRequest: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = {
            dismissRequest.invoke()
        }
    ) {
        Column(
            modifier = Modifier.navigationBarsPadding()
        ) {
            TitleBottomSheet(stringResource(R.string.cities))

            LazyColumn {
                items(data) { city ->
                    CheckRow(
                        text = city.name,
                        isCheck = currentCity == city.name
                    ) {
                        onClick.invoke(city)
                    }
                }
            }
        }
    }
}