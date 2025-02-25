package com.example.afishaapp.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.R

@Composable
fun SelectRow(
    text: String,
    paddingValues: PaddingValues = PaddingValues(0.dp, 0.dp),
    fontWeight: FontWeight = FontWeight.Medium,
    fontFamily: FontFamily = FontFamily.Default,
    fontSize: TextUnit = 15.sp,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                onClick = {
                    onClick.invoke()
                }
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(15.dp),
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontFamily = fontFamily
        )

        Icon(
            painter = painterResource(R.drawable.ic_chevron),
            contentDescription = null,
            modifier = Modifier
                .padding(0.dp, 0.dp, 15.dp, 0.dp)
                .align(Alignment.CenterVertically),
        )
    }
}