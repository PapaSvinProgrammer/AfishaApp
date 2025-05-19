package com.example.afishaapp.ui.screen.formPaymentScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.afishaapp.R
import com.example.afishaapp.app.utils.convertData.ConvertDate
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.row.SelectRow
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

    if (viewModel.isSuccess) {
        SuccessDialog(
            onDismiss = {
                viewModel.insertTicket()
                viewModel.updateSuccessState(false)
                navController.popBackStack()
            }
        )
    }

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
                            contentDescription = stringResource(R.string.arrow_back_description)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        viewModel.event?.let { event ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(10.dp))

                    HeadContent(
                        modifier = Modifier.padding(horizontal = DefaultPadding),
                        event = event
                    )
                    MainContent(
                        modifier = Modifier.padding(horizontal = DefaultPadding),
                        event = event
                    )
                    PaymentsMethods()
                    AgreementsContent(viewModel)
                }

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(horizontal = DefaultPadding),
                    enabled = viewModel.permission,
                    onClick = { viewModel.updateSuccessState(true) }
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
private fun SuccessDialog(onDismiss: () -> Unit) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_success))
    val progress by animateLottieCompositionAsState(composition)

    Dialog(onDismissRequest = {}) {
        Box {
            LottieAnimation(
                composition = composition,
                progress = { progress }
            )
        }
    }

    LaunchedEffect(progress) {
        if (progress == 1f) {
            onDismiss.invoke()
        }
    }
}

@Composable
private fun AgreementsContent(viewModel: FormPaymentViewModel) {
    Spacer(modifier = Modifier.height(20.dp))

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PaymentsMethods() {
    var isExpanded by remember { mutableStateOf(false) }

    Spacer(modifier = Modifier.height(20.dp))

    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = DefaultPadding),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = null,
                    indication = null,
                    onClick = { isExpanded = !isExpanded }
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.payment_method),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(DefaultPadding)
            )

            Box(modifier = Modifier.padding(DefaultPadding)) {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            }
        }

        if (isExpanded) {
            HorizontalDivider()

            PaymentCardRow("Через СПБ") {
                Icon(
                    painter = painterResource(R.drawable.ic_sbp),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color.Unspecified
                )
            }

            HorizontalDivider()

            PaymentCardRow("****5781") {
                Icon(
                    painter = painterResource(R.drawable.ic_mir),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
            }
        }

        HorizontalDivider()

        SelectRow(
            text = "Добавить новую карту",
            icon = Icons.Default.Add,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        ) { }
    }
}

@Composable
private fun PaymentCardRow(
    text: String,
    icon: @Composable () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceContainerHigh),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.padding(DefaultPadding)) {
            icon()

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = text,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Icon(
            imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.padding(DefaultPadding)
        )
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    event: Event
) {
    Spacer(modifier = Modifier.height(15.dp))
    InfoRow(
        modifier = modifier,
        title = stringResource(R.string.event_number),
        subtitle = event.id.toString()
    )

    Spacer(modifier = Modifier.height(10.dp))
    InfoRow(
        modifier = modifier,
        title = stringResource(R.string.price),
        subtitle = event.price
    )

    Spacer(modifier = Modifier.height(10.dp))
    InfoRow(
        modifier = modifier,
        title = stringResource(R.string.date_of_buy),
        subtitle = ConvertDate.convertDatePosted(System.currentTimeMillis() / 1000)
    )
}

@Composable
private fun HeadContent(
    modifier: Modifier = Modifier,
    event: Event
) {
    Column(modifier = modifier.fillMaxWidth()) {
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

@Composable
private fun InfoRow(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    fontSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
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