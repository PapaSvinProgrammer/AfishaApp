package com.example.afishaapp.ui.screen.bottomSheet

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.afishaapp.R
import com.example.afishaapp.app.utils.ConvertDate
import com.example.afishaapp.ui.widget.row.CheckRow
import com.example.afishaapp.ui.widget.text.TitleBottomSheet

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateBottomSheet(
    currentTime: Int,
    onClick: (Int) -> Unit,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest.invoke() }
    ) {
        Column {
            TitleBottomSheet(stringResource(R.string.shows_dates))

            repeat(4) {
                CheckRow(
                    text = ConvertDate.addDaysToCurrentDate(it),
                    isCheck = currentTime == it
                ) {
                    onClick.invoke(it)
                }
            }
        }
    }
}