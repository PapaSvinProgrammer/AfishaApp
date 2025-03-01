package com.example.afishaapp.ui.screen.bottomSheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.R
import com.example.afishaapp.domain.module.FilterState
import com.example.afishaapp.ui.widget.TitleBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterBottomSheet(
    currentFilter: FilterState,
    onClick: (FilterState) -> Unit,
    onDismissRequest: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest.invoke() }
    ) {
        Column {
            TitleBottomSheet(stringResource(R.string.filter))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClick.invoke(FilterState.RATING)
                    },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.by_rating),
                    modifier = Modifier.padding(15.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )

                if (currentFilter == FilterState.RATING) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 15.dp, 0.dp)
                            .align(Alignment.CenterVertically),
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClick.invoke(FilterState.TITLE)
                    },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.by_title),
                    modifier = Modifier.padding(15.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )

                if (currentFilter == FilterState.TITLE) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 15.dp, 0.dp)
                            .align(Alignment.CenterVertically),
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClick.invoke(FilterState.YEAR)
                    },
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.by_date),
                    modifier = Modifier.padding(15.dp),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )

                if (currentFilter == FilterState.YEAR) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 15.dp, 0.dp)
                            .align(Alignment.CenterVertically),
                    )
                }
            }
        }
    }
}