package com.example.afishaapp.ui.screen.bottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.afishaapp.R
import com.example.afishaapp.domain.module.TicketFilterType
import com.example.afishaapp.ui.widget.row.CheckRow
import com.example.afishaapp.ui.widget.text.TitleBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketFilterBottomSheet(
    currentFilter: TicketFilterType,
    onClick: (TicketFilterType) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(modifier = Modifier.navigationBarsPadding()) {
            Column {
                TitleBottomSheet(stringResource(R.string.filter))

                CheckRow(
                    text = "По дате покупки",
                    isCheck = currentFilter == TicketFilterType.BUY_DATE
                ) {
                    onClick.invoke(TicketFilterType.BUY_DATE)
                }

                CheckRow(
                    text = "По дате проведения",
                    isCheck = currentFilter == TicketFilterType.START_DATE
                ) {
                    onClick.invoke(TicketFilterType.START_DATE)
                }

                CheckRow(
                    text = "По имени",
                    isCheck = currentFilter == TicketFilterType.NAME
                ) {
                    onClick.invoke(TicketFilterType.NAME)
                }
            }
        }
    }
}