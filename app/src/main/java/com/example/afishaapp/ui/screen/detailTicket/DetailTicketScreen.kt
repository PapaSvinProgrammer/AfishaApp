package com.example.afishaapp.ui.screen.detailTicket

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.afishaapp.R
import com.example.afishaapp.app.navigation.EventRoute
import com.example.afishaapp.app.navigation.PlaceRoute
import com.example.afishaapp.data.room.TicketEntity
import com.example.afishaapp.domain.module.SettingsType
import com.example.afishaapp.ui.screen.bottomSheet.TicketSettingsBottomSheet
import com.example.afishaapp.ui.widget.card.HatchHorizontalDivider
import com.example.afishaapp.ui.widget.text.SubtitleTopBar
import com.example.afishaapp.ui.widget.text.TitleTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTicketScreen(
    navController: NavController,
    viewModel: DetailTicketViewModel,
    eventId: Int
) {
    viewModel.getTicketDetail(eventId)

    if (viewModel.deleteDialogState) {
        DeleteDialog(
            onDismiss = {
                viewModel.updateDeleteDialogState(false)
                navController.popBackStack()
            }
        )
    }

    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TitleTopBar(text = "Билет на")
                        SubtitleTopBar(text = viewModel.ticket?.name ?: "")
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.ic_arrow_back_content_description)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { viewModel.updateSettingsBottomSheetState(true) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = stringResource(R.string.settings)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    bottom = innerPadding.calculateBottomPadding(),
                    start = 10.dp,
                    end = 10.dp
                )
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen
                }
                .drawWithCache {
                    val path = Path()

                    path.addRect(
                        Rect(
                            topLeft = Offset.Zero,
                            bottomRight = Offset(size.width, size.height)
                        )
                    )

                    val dotSize = size.width / 18f

                    onDrawWithContent {
                        clipPath(path) {
                            this@onDrawWithContent.drawContent()
                        }

                        drawTicketView(
                            scope = this,
                            dotSize = dotSize,
                            k = 3f / 4f
                        )
                    }
                }
        ) {
            viewModel.ticket?.let {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                ) {
                    Column {
                        Column(
                            modifier = Modifier
                                .weight(3f)
                                .padding(10.dp)
                        ) {
                            TopContent(it)
                        }

                        HatchHorizontalDivider()

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(
                                    top = 25.dp,
                                    bottom = 10.dp,
                                    start = 15.dp,
                                    end = 15.dp
                                )
                        ) {
                            BottomContent()
                        }
                    }
                }
            }
        }

        if (viewModel.settingsBottomSheetState) {
            TicketSettingsBottomSheet(
                onDismissRequest = { viewModel.updateSettingsBottomSheetState(false) },
                onClick = { settingsHandle(it, navController, viewModel) }
            )
        }
    }
}

@Composable
private fun TopContent(ticket: TicketEntity) {
    Column {
        HeadTicket(ticket)

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = ticket.name,
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = ticket.address,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = ticket.phone,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(5.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            val icon = R.drawable.ic_metro_msk

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
                text = ticket.subway,
                textAlign = TextAlign.Start,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun HeadTicket(ticket: TicketEntity) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        AsyncImage(
            model = ticket.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(190.dp)
                .clip(RoundedCornerShape(10.dp))
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp)
        ) {
            Text(
                text = ticket.age,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Время",
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            )

            Text(
                text = "15:00-16:20",
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Длительность",
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            )

            Text(
                text = "2 час. 30 мин.",
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Стоимость",
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            )

            Text(
                text = ticket.price,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun BottomContent() {
    Image(
        painter = painterResource(R.drawable.images),
        contentScale = ContentScale.Crop,
        contentDescription = null,
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(10.dp))
    )
}

@Composable
private fun DeleteDialog(
    onDismiss: () -> Unit
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_remove))
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

private fun settingsHandle(
    type: SettingsType,
    navController: NavController,
    viewModel: DetailTicketViewModel
) {
    viewModel.ticket?.let {
        when (type) {
            SettingsType.DETAIL_PLACE -> {
                navController.navigate(
                    PlaceRoute(0)
                )
            }

            SettingsType.DETAIL_EVENT -> {
                navController.navigate(
                    EventRoute(it.eventId)
                )
            }

            SettingsType.DELETE -> {
                viewModel.updateDeleteDialogState(true)
                viewModel.deleteTicket()
            }
        }
    }
}

fun drawTicketView(
    scope: ContentDrawScope,
    dotSize: Float,
    k: Float
) {
    scope.apply {
        drawCircle(
            color = Color.Blue,
            radius = dotSize,
            center = Offset(
                x = dotSize / 2,
                y = size.height * k
            ),
            blendMode = BlendMode.Clear
        )

        drawCircle(
            color = Color.Blue,
            radius = dotSize,
            center = Offset(
                x = size.width - dotSize / 2,
                y = size.height * k
            ),
            blendMode = BlendMode.Clear
        )
    }
}