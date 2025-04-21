package com.example.afishaapp.ui.widget.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.R
import com.example.afishaapp.ui.theme.DefaultPadding

@Preview
@Composable
fun DetailTicketCard(
    onBack: () -> Unit = { }
) {
    Scaffold (
        topBar = {
            Box(
                modifier = Modifier.padding(
                    horizontal = DefaultPadding,
                    vertical = 10.dp
                )
            ) {
                ElevatedButton(
                    onClick = onBack,
                    shape = CircleShape,
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.size(48.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.ic_arrow_back_content_description)
                    )
                }
            }
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
            ElevatedCard(
                modifier = Modifier.fillMaxSize().padding(5.dp)
            ) {
                Column {
                    Column(
                        modifier = Modifier
                            .weight(3f)
                            .padding(10.dp)
                    ) {
                        TopContent()
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
}

@Composable
private fun TopContent() {
    Column {
        HeadTicket()

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Название мероприятия",
            fontWeight = FontWeight.Bold,
            fontSize = 19.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Адрес: ул. Пушкина дом Калатушкина",
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Номер: 127398127, 2189123",
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
                text = "Метро 1, метро 2, Метро 1, метро 2",
                textAlign = TextAlign.Start,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedButton(onClick = {}) {
            Text(text = "Подробнее о месте")
        }

        OutlinedButton(onClick = {}) {
            Text(text = "Подробнее о событиии")
        }

    }
}

@Composable
private fun HeadTicket() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(190.dp).clip(RoundedCornerShape(10.dp))
        )

        Column(
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp)
        ) {
            Text(
                text = "18+",
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
                text = "200 руб.",
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
        modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(10.dp))
    )
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