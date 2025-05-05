package com.example.afishaapp.ui.screen.bottomSheet.filter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.afishaapp.R
import com.example.afishaapp.domain.module.EventFilter
import com.example.afishaapp.ui.widget.row.CheckRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventFilterBottomSheet(
    currentFilter: EventFilter,
    onClick: (EventFilter) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(modifier = Modifier.navigationBarsPadding()) {
            CheckRow(
                text = stringResource(R.string.by_default),
                isCheck = currentFilter == EventFilter.DEFAULT
            ) { onClick(EventFilter.DEFAULT) }

            CheckRow(
                text = stringResource(R.string.by_name),
                isCheck = currentFilter == EventFilter.NAME
            ) { onClick(EventFilter.NAME) }
        }
    }
}