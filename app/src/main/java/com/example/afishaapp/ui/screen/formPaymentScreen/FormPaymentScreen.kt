package com.example.afishaapp.ui.screen.formPaymentScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.R
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.text.SubtitleTopBar
import com.example.afishaapp.ui.widget.text.TitleTopBar

var permission by mutableStateOf(false)

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun FormPaymentScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TitleTopBar(text = "Оформление заказа")
                        SubtitleTopBar(text = "220 руб.")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.ic_arrow_back_content_description)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                    start = DefaultPadding,
                    end = DefaultPadding
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Spacer(modifier = Modifier.height(10.dp))

                HeadContent()
                MainContent()
                AgreementsContent()
            }

            Button(
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
                enabled = permission,
                onClick = {}
            ) {
                Text(text = "Оформить заказ")
            }
        }
    }
}

@Composable
fun AgreementsContent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = permission,
            onClick = { permission = !permission}
        )

        Text(
            text = "Согласен с условиями Правил использования торговой площадки и правилами возврата",
            fontSize = 13.sp
        )
    }
}

@Composable
private fun MainContent() {
    Spacer(modifier = Modifier.height(15.dp))
    InfoRow(
        title = "Номер события",
        subtitle = "2133123"
    )

    Spacer(modifier = Modifier.height(10.dp))
    InfoRow(
        title = "Стоимость",
        subtitle = "220 руб."
    )

    Spacer(modifier = Modifier.height(10.dp))
    InfoRow(
        title = "Дата покупки",
        subtitle = "1 марта 2025"
    )

    Spacer(modifier = Modifier.height(20.dp))
    InfoRow(
        title = "Товары",
        subtitle = "Общая сумма",
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold
    )

    HorizontalDivider(
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
    )

    InfoRow(
        title = "Событие №123213 x1",
        subtitle = "220 руб.",
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    )

    HorizontalDivider(
        modifier = Modifier.fillMaxWidth().padding(vertical = 5.dp)
    )

    InfoRow(
        title = "Итого",
        subtitle = "220 руб.",
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    )
}

@Composable
private fun HeadContent() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp).clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(
                text = "Название события",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "14:20 - 15:20",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
private fun InfoRow(
    title: String,
    subtitle: String,
    fontSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            fontSize = fontSize,
            fontWeight = fontWeight
        )

        Text(
            text = subtitle,
            fontSize = fontSize,
            fontWeight = fontWeight
        )
    }
}