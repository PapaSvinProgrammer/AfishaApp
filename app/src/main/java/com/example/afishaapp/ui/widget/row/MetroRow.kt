package com.example.afishaapp.ui.widget.row

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.R
import com.example.afishaapp.app.support.metro
import com.example.afishaapp.data.module.place.Place
import com.example.afishaapp.ui.theme.DefaultPadding

@Composable
fun MetroRow(place: Place) {
    if (place.subway.isEmpty()) {
        SpareContent()
    }
    else {
        MainContent(place)
    }
}

@Composable
private fun SpareContent() {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        val icon = R.drawable.ic_error

        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(20.dp)
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp),
            text = stringResource(R.string.no_subway_nearby),
            textAlign = TextAlign.Start,
            fontSize = 15.sp
        )
    }
}

@Composable
private fun MainContent(place: Place) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        val icon = metro[place.location] ?: R.drawable.ic_error

        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(20.dp)
        )

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            text = place.subway,
            textAlign = TextAlign.Start,
            fontSize = 15.sp
        )
    }
}