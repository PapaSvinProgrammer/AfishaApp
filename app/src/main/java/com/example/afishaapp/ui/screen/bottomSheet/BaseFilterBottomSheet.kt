package com.example.afishaapp.ui.screen.bottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.afishaapp.R
import com.example.afishaapp.domain.module.BaseFilterType
import com.example.afishaapp.ui.widget.text.TitleBottomSheet
import com.example.afishaapp.ui.widget.row.CheckRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseFilterBottomSheet(
    currentFilter: BaseFilterType,
    onClick: (BaseFilterType) -> Unit,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest.invoke() }
    ) {
        Column(
            modifier = Modifier.navigationBarsPadding()
        ) {
            TitleBottomSheet(stringResource(R.string.filter))

            CheckRow(
                text = stringResource(R.string.by_rating),
                isCheck = currentFilter == BaseFilterType.RATING
            ) {
                onClick.invoke(BaseFilterType.RATING)
            }

            CheckRow(
                text = stringResource(R.string.by_title),
                isCheck = currentFilter == BaseFilterType.TITLE
            ) {
                onClick.invoke(BaseFilterType.TITLE)
            }

            CheckRow(
                text = stringResource(R.string.by_date),
                isCheck = currentFilter == BaseFilterType.YEAR
            ) {
                onClick.invoke(BaseFilterType.YEAR)
            }
        }
    }
}