package com.example.afishaapp.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
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
import com.example.afishaapp.app.navigation.AccountRoute
import com.example.afishaapp.app.navigation.EventListRoute
import com.example.afishaapp.app.navigation.MovieListRoute
import com.example.afishaapp.ui.screen.bottomSheet.CategoryEventBottomSheet
import com.example.afishaapp.ui.screen.bottomSheet.CityBottomSheet
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
    viewModel.getEvents(viewModel.defaultCity, viewModel.currentCategory)
    viewModel.getCity()
    viewModel.getDefaultCity()
    viewModel.getMovies(viewModel.defaultCity)
    viewModel.getCategories()

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
                    IconButton(onClick = { navController.navigate(AccountRoute) }) {
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
                .padding(
                    0.dp,
                    padding.calculateTopPadding(),
                    0.dp,
                    innerPadding.calculateBottomPadding()
                )
                .verticalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
            ) {
                FilledTonalButton(
                    modifier = Modifier.padding(10.dp, 0.dp),
                    shape = RoundedCornerShape(10.dp),
                    onClick = { }
                ) {
                    Text(text = stringResource(R.string.date))
                }

                FilledTonalButton(
                    onClick = { viewModel.updateCategoryState(true) },
                    shape = RoundedCornerShape(10.dp),
                ) {
                    Text(
                        text = viewModel.currentCategory.name,
                        maxLines = 1
                    )

                    Icon(
                        painter = painterResource(R.drawable.ic_keyboard_arrow_down),
                        contentDescription = null
                    )
                }
            }

            SelectRow(
                text = stringResource(R.string.future_event),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ) {
                navController.navigate(
                    EventListRoute(
                        title = "События",
                        categorySlug = viewModel.currentCategory.slug,
                        location = viewModel.city.first { it.name == viewModel.defaultCity }.slug
                    )
                )
            }

            LazyRow(
                contentPadding = PaddingValues(10.dp, 0.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                viewModel.eventResponse?.let {
                    items(it.results) { event ->
                        EventCardRow(event) {

                        }
                    }
                }
            }

            SelectRow(
                text = stringResource(R.string.expected_in_cinema),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                paddingValues = PaddingValues(0.dp, 5.dp)
            ) {
                navController.navigate(
                    MovieListRoute(
                        title = "В кино",
                        location = viewModel.city.first { it.name == viewModel.defaultCity }.slug
                    )
                )
            }

            LazyRow(
                contentPadding = PaddingValues(10.dp, 0.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                viewModel.movieResponse?.let {
                    items(it.results) { movie ->
                        MovieCardRow(movie) {

                        }
                    }
                }
            }

            SelectRow(
                text = stringResource(R.string.concerts),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                paddingValues = PaddingValues(0.dp, 15.dp, 0.dp, 0.dp)
            ) {

            }

            LazyRow(
                contentPadding = PaddingValues(10.dp, 0.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                viewModel.eventConcert?.let {
                    items(it.results) { event ->
                        EventCardRow(event) {

                        }
                    }
                }
            }

            SelectRow(
                text = stringResource(R.string.exhibitions),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                paddingValues = PaddingValues(0.dp, 15.dp, 0.dp, 0.dp)
            ) {

            }

            LazyRow(
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 20.dp),
                contentPadding = PaddingValues(10.dp, 0.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                viewModel.eventExhibition?.let {
                    items(it.results) { event ->
                        EventCardRow(event) {

                        }
                    }
                }
            }
        }

        if (viewModel.cityBottomSheetState) {
            CityBottomSheet(
                data = viewModel.city,
                currentCity = viewModel.defaultCity,
                dismissRequest = { viewModel.updateCityState(false) },
                onClick = {
                    viewModel.updateDefaultCity(it)
                    viewModel.updateCityState(false)
                }
            )
        }

        if (viewModel.categoryBottomSheetState) {
            CategoryEventBottomSheet(
                data = viewModel.categoryEvent,
                currentCategory = viewModel.currentCategory,
                onDismiss = { viewModel.updateCategoryState(false) },
                onClick = {
                    viewModel.updateCurrentCategory(it)
                    viewModel.updateCategoryState(false)
                }
            )
        }
    }
}