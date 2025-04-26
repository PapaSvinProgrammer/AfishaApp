package com.example.afishaapp.ui.screen.bottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.afishaapp.R
import com.example.afishaapp.domain.module.SettingsType
import com.example.afishaapp.domain.module.TicketFilterType
import com.example.afishaapp.ui.widget.row.CheckRow
import com.example.afishaapp.ui.widget.text.TitleBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketSettingsBottomSheet(
    onDismissRequest: () -> Unit,
    onClick: (SettingsType) -> Unit
) {
    ModalBottomSheet(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier.navigationBarsPadding()
        ) {
            TitleBottomSheet(stringResource(R.string.settings))

            CheckRow(
                text = "Подробности события",
                isCheck = false
            ) {
                onClick.invoke(SettingsType.DETAIL_EVENT)
            }

            CheckRow(
                text = "Подробности места",
                isCheck = false
            ) {
                onClick.invoke(SettingsType.DETAIL_PLACE)
            }

            CheckRow(
                text = "Вернуть билет",
                isCheck = false
            ) {
                onClick.invoke(SettingsType.DELETE)
            }
        }
    }
}