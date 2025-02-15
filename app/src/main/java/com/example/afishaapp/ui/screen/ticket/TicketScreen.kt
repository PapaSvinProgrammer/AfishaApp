package com.example.afishaapp.ui.screen.ticket

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.afishaapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketScreen() {
    CenterAlignedTopAppBar(
        title = { Text(text = stringResource(R.string.my_ticket_text)) },
    )
}