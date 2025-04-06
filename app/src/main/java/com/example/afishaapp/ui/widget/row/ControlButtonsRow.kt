package com.example.afishaapp.ui.widget.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.afishaapp.R
import com.example.afishaapp.ui.theme.DefaultPadding

@Composable
fun ControlButtonsRow(
    mapButtonClick: () -> Unit,
    moreButtonClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 20.dp,
                start = DefaultPadding,
                end = DefaultPadding
            ),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(
            onClick = mapButtonClick,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = stringResource(R.string.route))
        }

        Button(
            onClick = moreButtonClick,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(text = stringResource(R.string.more_detail))
        }
    }
}