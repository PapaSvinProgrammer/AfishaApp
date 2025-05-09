package com.example.afishaapp.ui.screen.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp
import com.example.afishaapp.R
import com.example.afishaapp.data.module.Category

@Composable
fun SearchFilterDialog(
    onDismissClick: () -> Unit,
    onConfirmClick: (MutableList<Category>) -> Unit
) {
    val searchCategory = listOf(
        Category(
            id = -1,
            slug = "event",
            name = "События"
        ),
        Category(
            id = -1,
            slug = "place",
            name = "Места"
        )
    )

    val childCheckedStates = remember { mutableStateListOf(true, true) }

    AlertDialog(
        title = {
            Text(text = stringResource(R.string.settings))
        },
        text = {
            Column {
                HorizontalDivider(color = Color.DarkGray)
                CheckboxContent(searchCategory, childCheckedStates)
                HorizontalDivider(color = Color.DarkGray)
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val res = mutableListOf<Category>()

                childCheckedStates.forEachIndexed { index, b ->
                    if (b) {
                        res.add(searchCategory[index])
                    }
                }

                onConfirmClick(res)
            }) {
                Text(text = stringResource(R.string.apply))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissClick) {
                Text(text = stringResource(R.string.cancel))
            }
        },
        onDismissRequest = onDismissClick
    )
}

@Composable
fun CheckboxContent(
    category: List<Category>,
    childCheckedStates: SnapshotStateList<Boolean>
) {
    val parentState = when {
        childCheckedStates.all { it } -> ToggleableState.On
        childCheckedStates.none { it } -> ToggleableState.Off
        else -> ToggleableState.Indeterminate
    }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TriStateCheckbox(
                state = parentState,
                onClick = {
                    val newState = parentState != ToggleableState.On

                    childCheckedStates.forEachIndexed { index, _ ->
                        childCheckedStates[index] = newState
                    }
                }
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(stringResource(R.string.all))
        }

        childCheckedStates.forEachIndexed { index, checked ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = checked,
                    onCheckedChange = { isChecked ->
                        childCheckedStates[index] = isChecked
                    }
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(text = category[index].name)
            }
        }
    }
}