package com.example.afishaapp.ui.widget.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.app.support.ConvertInfo
import com.example.afishaapp.ui.theme.DefaultPadding

@Composable
fun EventDescriptionText(
    title: String,
    bodyText: String
) {
    Text(
        text = ConvertInfo.convertTitle(title),
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        modifier = Modifier.padding(DefaultPadding, 0.dp, DefaultPadding, 5.dp)
    )

    Text(
        text = bodyText,
        fontSize = 14.sp,
        maxLines = 4,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.padding(DefaultPadding, 0.dp)
    )
}