package com.example.afishaapp.ui.screen.movieShowBottomSheet

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.afishaapp.R
import com.example.afishaapp.app.utils.convertData.ConvertDate
import com.example.afishaapp.di.viewModel.ViewModelFactory
import com.example.afishaapp.ui.screen.bottomSheet.DateBottomSheet
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.listItem.ShowCard
import com.example.afishaapp.ui.widget.chip.SelectDropDown

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieShowBottomSheet(
    viewModelFactory: ViewModelFactory,
    movieId: Int,
    onDismiss: () -> Unit,
    onClick: (Int) -> Unit
) {
    val viewModel: MovieShowViewModel = viewModel(factory = viewModelFactory)

    val newTime: Int by remember {
        derivedStateOf {
            viewModel.selectTime
        }
    }

    viewModel.getLocationSlug()

    LaunchedEffect(newTime) {
        viewModel.getMovieShows(movieId, newTime.toLong())
    }

    ModalBottomSheet(
        onDismissRequest = { onDismiss.invoke() }
    ) {
        Column(
            modifier = Modifier.navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val text = if (viewModel.selectTime== 0)
                stringResource(R.string.today)
            else
                ConvertDate.addDaysToCurrentDate(viewModel.selectTime)

            SelectDropDown(text) {
                viewModel.updateDateBottomSheetState(true)
            }

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(DefaultPadding),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                items(viewModel.shows) { show ->
                    ShowCard(
                        show = show,
                        onClick = { onClick.invoke(show.id) }
                    )
                }
            }
        }
    }

    if (viewModel.dateBottomSheetState) {
        DateBottomSheet(
            currentTime = viewModel.selectTime,
            onClick = {
                viewModel.updateSelectTime(it)
                viewModel.updateDateBottomSheetState(false)
            },
            onDismissRequest = {
                viewModel.updateDateBottomSheetState(false)
            }
        )
    }
}