package com.example.afishaapp.ui.screen.ticket

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.afishaapp.R
import com.example.afishaapp.ui.screen.main.bottomBarVisibilityState
import com.example.afishaapp.ui.widget.card.DetailTicketCard
import com.example.afishaapp.ui.widget.card.TicketCard
import com.example.afishaapp.ui.widget.text.TitleTopBar

@Preview(showSystemUi = true)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun TicketScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp)
) {
    var showDetail by remember { mutableStateOf(false) }
    var topBarVisibilityState by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = topBarVisibilityState,
                enter = slideInVertically(),
                exit = slideOutVertically()
            ) {
                CenterAlignedTopAppBar(
                    title = { TitleTopBar(stringResource(R.string.my_ticket_text)) },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_sort),
                                contentDescription = stringResource(R.string.search_title_text)
                            )
                        }

                        IconButton(onClick = { }) {
                            Icon(
                                imageVector = Icons.Rounded.Search,
                                contentDescription = stringResource(R.string.search_title_text)
                            )
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        SharedTransitionLayout {
            Column {
                Spacer(modifier = Modifier.height(innerPadding.calculateTopPadding()))

                AnimatedContent(
                    targetState = showDetail
                ) { targetState ->
                    if (targetState) {
                        bottomBarVisibilityState.value = false
                        topBarVisibilityState = false

                        DetailTicketCard(
                            onBack = { showDetail = false }
                        )
                    }
                    else {
                        bottomBarVisibilityState.value = true
                        topBarVisibilityState = true

                        MainContent(
                            onShowDetail = { showDetail = true },
                            animatedVisibilityScope = this@AnimatedContent,
                            sharedTransitionScope = this@SharedTransitionLayout
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun MainContent(
    onShowDetail: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope
) {
    with(sharedTransitionScope) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(5.dp),
             modifier = Modifier
                 .sharedElement(
                     state = rememberSharedContentState(key = "key"),
                     animatedVisibilityScope = animatedVisibilityScope
                 )
                 .padding(horizontal = 10.dp)
        ) {
            items(10) {
                TicketCard(onClick = onShowDetail)
            }
        }
    }
}