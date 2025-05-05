package com.example.afishaapp.ui.widget.row

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.R

@Composable
fun CheckRow(
    text: String,
    isCheck: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick.invoke() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(15.dp),
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )

        if (isCheck) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = stringResource(R.string.ic_check_content_description),
                modifier = Modifier
                    .padding(end = 15.dp)
                    .align(Alignment.CenterVertically),
            )
        }
    }
}