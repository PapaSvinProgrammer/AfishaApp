package com.example.afishaapp.ui.screen.formPaymentScreen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.example.afishaapp.R
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.text.SubtitleTopBar
import com.example.afishaapp.ui.widget.text.TitleTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormPaymentScreen(
    navController: NavController,
    viewModel: FormPaymentViewModel,
    eventId: Int
) {
    viewModel.getEvent(eventId)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TitleTopBar(text = stringResource(R.string.placing_order))
                        SubtitleTopBar(text = viewModel.event?.price ?: "")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.ic_arrow_back_content_description)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        viewModel.event?.let { event ->
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

                    HeadContent(event)
                    MainContent(event)
                    PaymentsMethods()
                    AgreementsContent(viewModel)
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    enabled = viewModel.permission,
                    onClick = {}
                ) {
                    Text(
                        text = stringResource(R.string.place_order)
                    )
                }
            }
        }
    }
}

@Composable
fun AgreementsContent(viewModel: FormPaymentViewModel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = viewModel.permission,
            onClick = { viewModel.updatePermission(!viewModel.permission)}
        )

        Text(
            text = "Согласен с условиями Правил использования торговой площадки и правилами возврата",
            fontSize = 13.sp
        )
    }
}

@Composable
private fun PaymentsMethods() {
    Spacer(modifier = Modifier.height(20.dp))

    Text(
        text = stringResource(R.string.payment_method),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(10.dp))

    OutlinedButton(
        modifier = Modifier.width(210.dp),
        onClick = {}
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_sbp),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = stringResource(R.string.with_sbp),
            color = Color.Black,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        )
    }

    OutlinedButton(
        modifier = Modifier.width(210.dp),
        onClick = {}
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_mir),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )

        Text(
            text = stringResource(R.string.with_card),
            color = Color.Black,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        )
    }
}

@Composable
private fun MainContent(event: Event) {
    Spacer(modifier = Modifier.height(15.dp))
    InfoRow(
        title = stringResource(R.string.event_number),
        subtitle = event.id.toString()
    )

    Spacer(modifier = Modifier.height(10.dp))
    InfoRow(
        title = stringResource(R.string.price),
        subtitle = event.price
    )

    Spacer(modifier = Modifier.height(10.dp))
    InfoRow(
        title = stringResource(R.string.date_of_buy),
        subtitle = "1 марта 2025"
    )
}

@Composable
private fun HeadContent(event: Event) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = event.images.first().image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .size(150.dp)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column {
            Text(
                text = event.shortTitle,
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