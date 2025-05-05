package com.example.afishaapp.ui.screen.bottomSheet.filter

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.afishaapp.R
import com.example.afishaapp.domain.module.MovieFilter
import com.example.afishaapp.ui.widget.row.CheckRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieFilterBottomSheet(
    currentFilter: MovieFilter,
    onClick: (MovieFilter) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss
    ) {
        CheckRow(
            text = stringResource(R.string.by_default),
            isCheck = currentFilter == MovieFilter.DEFAULT
        ) { onClick(MovieFilter.DEFAULT) }

        CheckRow(
            text = stringResource(R.string.by_rating),
            isCheck = currentFilter == MovieFilter.RATING
        ) { onClick(MovieFilter.RATING) }

        CheckRow(
            text = stringResource(R.string.by_year),
            isCheck = currentFilter == MovieFilter.YEAR
        ) { onClick(MovieFilter.YEAR) }
    }
}