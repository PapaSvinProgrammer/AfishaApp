package com.example.afishaapp.ui.widget.text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.ui.theme.acidFontFamily

@Composable
fun TitleBottomSheet(text: String) {
    Text(
        text = text,
        fontFamily = acidFontFamily,
        fontSize = 28.sp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 10.dp),
        textAlign = TextAlign.Center
    )
}