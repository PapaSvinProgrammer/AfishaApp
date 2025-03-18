package com.example.afishaapp.ui.widget.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextDescription(description: String, bodyText: String) {
    Text(
        text = description,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        modifier = Modifier.padding(bottom = 5.dp)
    )

    Text(
        text = bodyText,
        fontSize = 15.sp,
        modifier = Modifier.padding(bottom = 5.dp)
    )
}