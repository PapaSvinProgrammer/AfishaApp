package com.example.afishaapp.ui.screen.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.app.navigation.NavRoutes
import com.example.afishaapp.ui.screen.cityBottomSheet.CityBottomSheet
import com.example.afishaapp.ui.widget.EventCardRow
import com.example.afishaapp.ui.widget.MovieCardRow
import com.example.afishaapp.ui.widget.SelectRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    innerPadding: PaddingValues
) {
    viewModel.getCity()
    viewModel.getDefaultCity()
    viewModel.getEvents()
    viewModel.getMovieShows()

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(10.dp, 0.dp, 0.dp, 0.dp)
                            .clip(CircleShape)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple(),
                                onClick = {
                                    viewModel.updateCityState(true)
                                }
                            )
                    ) {
                        Text(
                            text = viewModel.defaultCity,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(5.dp, 0.dp, 0.dp, 0.dp)
                        )
                        Icon(
                            painter = painterResource(R.drawable.ic_keyboard_arrow_down),
                            contentDescription = "City list",
                            modifier = Modifier.padding(5.dp, 0.dp)
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { navController.navigate(NavRoutes.ACCOUNT.name) }) {
                        Icon(
                            painter = painterResource(R.drawable.ic_account),
                            contentDescription = stringResource(R.string.ic_account_content_description)
                        )
                    }
                },
                title = { Text(text = "") }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                FilledTonalButton(
                    modifier = Modifier.padding(10.dp, 0.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { }
                ) {
                    Text(text = stringResource(R.string.date))
                }

                FilledTonalButton(
                    onClick = { },
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(text = "Все события")
                    Icon(
                        painter = painterResource(R.drawable.ic_keyboard_arrow_down),
                        contentDescription = ""
                    )
                }
            }

            SelectRow(
                text = stringResource(R.string.future_event),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ) {

            }

            LazyRow {
                viewModel.eventResponse?.let {
                    items(it.results) { event ->
                        EventCardRow(event) {

                        }
                    }
                }
            }

            SelectRow(
                text = "Ожидается в кино",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                paddingValues = PaddingValues(0.dp, 5.dp)
            ) {

            }

            LazyRow {
                viewModel.movieShowResponse?.let {
                    items(it.results) { movieShow ->
                        MovieCardRow(movieShow) {

                        }
                    }
                }
            }
        }

        if (viewModel.cityBottomSheetState) {
            CityBottomSheet(
                data = viewModel.city,
                currentCity = viewModel.defaultCity,
                onClick = { viewModel.updateDefaultCity(it) },
                dismissRequest = { viewModel.updateCityState(it) }
            )
        }
    }
}