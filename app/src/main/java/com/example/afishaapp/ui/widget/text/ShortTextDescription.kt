package com.example.afishaapp.ui.widget.text

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.R
import com.example.afishaapp.app.utils.convertData.ConvertInfo
import com.example.afishaapp.ui.theme.DefaultPadding

@Composable
fun EventDescriptionText(
    title: String,
    bodyText: String,
    clickable: () -> Unit
) {
    Column(
        modifier = Modifier
            .padding(DefaultPadding)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { clickable.invoke() }
            )
    ) {
        Text(
            text = ConvertInfo.convertTitle(title),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            modifier = Modifier.padding(bottom = 5.dp)
        )

        Text(
            text = bodyText,
            fontSize = 14.sp,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = stringResource(R.string.read_more),
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}