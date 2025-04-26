package com.example.afishaapp.ui.screen.aboutEvent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.app.utils.ConvertCountTitle
import com.example.afishaapp.app.utils.ConvertDate
import com.example.afishaapp.app.utils.ConvertInfo
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.chip.AboutChipInfo
import com.example.afishaapp.ui.widget.shimmer.screen.ShimmerAboutEvent
import com.example.afishaapp.ui.widget.text.DefaultDetailDescription
import com.example.afishaapp.ui.widget.text.SubtitleTopBar
import com.example.afishaapp.ui.widget.text.TextDescription
import com.example.afishaapp.ui.widget.text.TitleTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutEventScreen(
    id: Int,
    viewModel: AboutEventViewModel,
    navController: NavController
) {
    viewModel.getCategory()
    viewModel.getEvent(id)
    viewModel.parseInf()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        TitleTopBar(stringResource(R.string.about_event))
                        SubtitleTopBar(text = viewModel.event?.shortTitle.toString())
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.ic_arrow_back_content_description)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(
                    start = DefaultPadding,
                    end = DefaultPadding,
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding()
                )
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            if (viewModel.event == null) {
                ShimmerAboutEvent()
            }

            viewModel.event?.let { event ->
                Spacer(modifier = Modifier.height(10.dp))

                InfoChipRow(event)

                TextDescription(
                    description = viewModel.parseEventDescription,
                    bodyText = viewModel.parseEventBodyText
                )

                DefaultDetailDescription(
                    title = stringResource(R.string.date),
                    subtitle = ConvertDate.convertListDateRange(event.dates)
                )

                DefaultDetailDescription(
                    title = stringResource(R.string.age_restriction),
                    subtitle = event.ageRestriction
                )

                ListCategoriesDetailDescription(
                    event = event,
                    viewModel = viewModel
                )

                ListDetailDescription(
                    title = stringResource(R.string.tags),
                    data = event.tags
                )
            }
        }
    }
}

@Composable
private fun InfoChipRow(event: Event) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Box(
            modifier = Modifier.weight(1f)
        ) {
            AboutChipInfo(
                title = ConvertCountTitle.convertLikeCount(event.favoritesCount),
                subTitle = ConvertCountTitle.convertCommentsCount(event.commentsCount)
            )
        }

        Box(
            modifier = Modifier.weight(1f)
        ) {
            AboutChipInfo(
                title = stringResource(R.string.price),
                subTitle =  ConvertInfo.convertPrice(event.price)
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ListDetailDescription(title: String, data: List<String>) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        modifier = Modifier.padding(top = 15.dp)
    )

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        data.forEach {
            SuggestionChip(
                label = {
                    Text(
                        text = ConvertInfo.convertTitle(it),
                        fontSize = 14.sp
                    )
                },
                onClick = { }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ListCategoriesDetailDescription(event: Event, viewModel: AboutEventViewModel) {
    Text(
        text = stringResource(R.string.categories),
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(top = 15.dp)
    )

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        event.categories.forEach {
            SuggestionChip(
                label = {
                    Text(
                        text = ConvertInfo.convertTitle(viewModel.categories[it].toString()),
                        fontSize = 14.sp
                    )
                },
                onClick = { }
            )
        }
    }
}