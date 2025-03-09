package com.example.afishaapp.ui.screen.bottomSheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.afishaapp.data.module.movieShow.Show
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.card.ShowCard
import com.example.afishaapp.ui.widget.text.TitleBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieShowBottomSheet(
    data: List<Show>,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss.invoke() }
    ) {
        Column(
            modifier = Modifier.navigationBarsPadding()
        ) {
            TitleBottomSheet(text = "Расписание показов")

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(DefaultPadding),
                verticalArrangement = Arrangement.spacedBy(30.dp)
            ) {
                items(data) { show ->
                    ShowCard(show)
                }
            }
        }
    }
}