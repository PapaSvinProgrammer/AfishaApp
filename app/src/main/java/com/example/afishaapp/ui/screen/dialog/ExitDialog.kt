package com.example.afishaapp.ui.screen.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.afishaapp.R

@Composable
fun ExitDialog(
    onDismissClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    AlertDialog(
        title = {
            Text(text = stringResource(R.string.exit))
        },
        text = {
            Text(text = stringResource(R.string.exit_text))
        },
        confirmButton = {
            TextButton(
                onClick = onConfirmClick
            ) {
                Text(text = stringResource(R.string.exiting))
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