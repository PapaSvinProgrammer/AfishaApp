package com.example.afishaapp.ui.screen.favorite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.afishaapp.R

private var selectedIndex by mutableIntStateOf(0)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoriteScreen() {
    val ticketOptions = stringArrayResource(R.array.ticket_segmented_button)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(
            title = { Text(text = stringResource(R.string.favorite_text)) },
        )

        SingleChoiceSegmentedButtonRow(
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
        ) {
            ticketOptions.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(
                        index = index,
                        count = ticketOptions.size
                    ),
                    onClick = { selectedIndex = index },
                    selected = index == selectedIndex,
                    label = { Text(text = label) }
                )
            }
        }
    }
}