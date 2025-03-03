package com.example.afishaapp.ui.screen.bottomSheet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.R
import com.example.afishaapp.data.module.Category
import com.example.afishaapp.ui.widget.TitleBottomSheet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryEventBottomSheet(
    data: List<Category>,
    currentCategory: Category,
    onClick: (Category) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = { onDismiss.invoke() },
        sheetState = rememberModalBottomSheetState()
    ) {
        Column {
            TitleBottomSheet(stringResource(R.string.categories))

            LazyColumn {
                items(data) { category ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onClick.invoke(category)
                            },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = category.name,
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(15.dp)
                        )

                        if (currentCategory.slug == category.slug) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(15.dp, 0.dp)
                                    .align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        }
    }
}