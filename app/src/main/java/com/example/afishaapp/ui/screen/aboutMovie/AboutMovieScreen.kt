package com.example.afishaapp.ui.screen.aboutMovie

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.app.utils.convertData.ConvertCountTitle
import com.example.afishaapp.app.utils.convertData.ConvertDate
import com.example.afishaapp.app.utils.convertData.ConvertInfo
import com.example.afishaapp.data.module.Genre
import com.example.afishaapp.data.module.movie.Movie
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.chip.AboutChipInfo
import com.example.afishaapp.ui.widget.chip.ChipRating
import com.example.afishaapp.ui.widget.shimmer.screen.ShimmerAboutEvent
import com.example.afishaapp.ui.widget.text.DefaultDetailDescription
import com.example.afishaapp.ui.widget.text.SubtitleTopBar
import com.example.afishaapp.ui.widget.text.TextDescription
import com.example.afishaapp.ui.widget.text.TitleTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutMovieScreen(
    viewModel: AboutMovieViewModel,
    navController: NavController,
    id: Int
) {
    viewModel.getMovie(id)
    viewModel.parseInfo()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        TitleTopBar(stringResource(R.string.about_movie))
                        SubtitleTopBar(viewModel.movie?.title.toString())
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.arrow_back_description)
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
            if (viewModel.movie == null) {
                ShimmerAboutEvent()
            }

            viewModel.movie?.let {
                Spacer(modifier = Modifier.height(10.dp))

                InfoChipRow(it)

                TextDescription(
                    description = viewModel.parseDescription,
                    bodyText = viewModel.parseBodyText
                )

                DefaultDetailDescription(
                    title = stringResource(R.string.year),
                    subtitle = it.year.toString()
                )

                DefaultDetailDescription(
                    title = stringResource(R.string.country),
                    subtitle = it.country
                )

                DefaultDetailDescription(
                    title = stringResource(R.string.duration),
                    subtitle = ConvertDate.convertDurationMinutes(it.runningTime)
                )

                DefaultDetailDescription(
                    title = stringResource(R.string.age_restriction),
                    subtitle = it.ageRestriction
                )

                DefaultDetailDescription(
                    title = stringResource(R.string.rating_MPAA),
                    subtitle = it.mpaaRating.ifEmpty { stringResource(R.string.no_info) }
                )

                DefaultDetailDescription(
                    title = stringResource(R.string.director),
                    subtitle = it.director
                )

                DefaultDetailDescription(
                    title = stringResource(R.string.writer),
                    subtitle = it.writer
                )

                DefaultDetailDescription(
                    title = stringResource(R.string.cast),
                    subtitle = it.stars
                )

                ListDetailDescription(it.genres)
            }
        }
    }
}

@Composable
private fun InfoChipRow(movie: Movie) {
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
                title = ConvertCountTitle.convertLikeCount(movie.favoritesCount),
                subTitle = ConvertCountTitle.convertCommentsCount(movie.commentsCount),
                icon = {
                    ChipRating(
                        rating = movie.imdbRating.toString(),
                        shape = CircleShape,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                            .size(42.dp)
                            .align(Alignment.Center)
                    )
                }
            )
        }

        Box(
            modifier = Modifier.weight(1f)
        ) {
            AboutChipInfo(
                title = stringResource(R.string.rating_IMDB),
                subTitle =  movie.imdbRating.toString(),
                icon = {
                    Icon(
                        painter = painterResource(R.drawable.ic_imdb_logo),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.padding(bottom = 10.dp).size(42.dp)
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ListDetailDescription(data: List<Genre>) {
    Text(
        text = stringResource(R.string.genres),
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
                        text = ConvertInfo.convertTitle(it.name),
                        fontSize = 14.sp
                    )
                },
                onClick = { }
            )
        }
    }
}