package com.example.afishaapp.ui.screen.cityBottomSheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.R
import com.example.afishaapp.data.module.City

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityBottomSheet(
    data: List<City>,
    currentCity: String,
    onClick: (city: City) -> Unit,
    dismissRequest: (Boolean) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = {
            dismissRequest.invoke(false)
        },
        sheetState = rememberModalBottomSheetState()
    ) {
        LazyColumn {
            items(data) { city ->
                Row(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .clickable {
                            onClick.invoke(city)
                        },
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(15.dp),
                        text = city.name,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )

                    if (currentCity == city.name) {
                        Icon(
                            painter = painterResource(R.drawable.ic_check),
                            contentDescription = stringResource(R.string.ic_check_content_description),
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(15.dp, 0.dp)
                        )
                    }
                }
            }
        }
    }
}