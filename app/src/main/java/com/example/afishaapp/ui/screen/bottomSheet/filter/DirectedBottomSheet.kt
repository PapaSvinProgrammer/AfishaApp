package com.example.afishaapp.ui.screen.bottomSheet.filter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.afishaapp.R
import com.example.afishaapp.domain.module.DirectedFilterType
import com.example.afishaapp.ui.widget.text.TitleBottomSheet
import com.example.afishaapp.ui.widget.row.CheckRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DirectedFilterBottomSheet(
    currentFilter: DirectedFilterType,
    onDismiss: () -> Unit,
    onClick: (DirectedFilterType) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss.invoke() }
    ) {
        Column(
            modifier = Modifier.navigationBarsPadding()
        ) {
            TitleBottomSheet(stringResource(R.string.filter))

            CheckRow(
                text = "Сначала новые",
                isCheck = currentFilter == DirectedFilterType.DESC
            ) {
                onClick.invoke(DirectedFilterType.DESC)
            }

            CheckRow(
                text = "Сначала старые",
                isCheck = currentFilter == DirectedFilterType.ASC
            ) {
                onClick.invoke(DirectedFilterType.ASC)
            }
        }
    }
}