package com.example.afishaapp.ui.screen.eventList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.ui.widget.EventCardFill

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventListScreen(
    navController: NavController,
    viewModel: EventListViewModel,
    topTitle: String,
    categorySlug: String,
    location: String
) {
    viewModel.getEvents(location, categorySlug)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = topTitle) },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_arrow_back),
                            contentDescription = stringResource(R.string.ic_arrow_back_content_description)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {  }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_calendar),
                            contentDescription = stringResource(R.string.ic_arrow_back_content_description)
                        )
                    }

                    IconButton(
                        onClick = {  }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_filter),
                            contentDescription = stringResource(R.string.ic_arrow_back_content_description)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(10.dp, 0.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            viewModel.events?.let {
                items(it.results) { event ->
                    EventCardFill(event) { }
                }
            }
        }
    }
}