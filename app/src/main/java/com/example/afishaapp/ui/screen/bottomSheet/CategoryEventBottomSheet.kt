package com.example.afishaapp.ui.screen.bottomSheet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.afishaapp.R
import com.example.afishaapp.data.module.Category
import com.example.afishaapp.ui.widget.text.TitleBottomSheet
import com.example.afishaapp.ui.widget.row.CheckRow

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
        Column(
            modifier = Modifier.navigationBarsPadding()
        ) {
            TitleBottomSheet(stringResource(R.string.categories))

            LazyColumn {
                items(data) { category ->
                    CheckRow(
                        text = category.name,
                        isCheck = currentCategory.slug == category.slug
                    ) {
                        onClick.invoke(category)
                    }
                }
            }
        }
    }
}