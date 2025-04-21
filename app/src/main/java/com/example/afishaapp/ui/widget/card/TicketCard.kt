package com.example.afishaapp.ui.widget.card

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.R

@Composable
fun TicketCard(
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
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
                        k = 1f / 2f
                    )
                }
            }
    ) {
        ElevatedCard(
            onClick = onClick,
            modifier = Modifier.fillMaxSize().padding(5.dp)
        ) {
            Box(
                modifier = Modifier.padding(10.dp).fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    TopContent()
                }

                Column(
                    modifier = Modifier.align(Alignment.BottomStart)
                ) {
                    BottomContent()
                }
            }
        }

        HatchHorizontalDivider()
    }
}

@Composable
private fun TopContent() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Name Event",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "18+",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )
    }

    Spacer(modifier = Modifier.height(10.dp))

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "12:40 - 13:50",
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )

        Box(
            modifier = Modifier
                .size(7.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )

        Text(
            text = "23 февраля",
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp
        )
    }
}

@Composable
private fun BottomContent() {
    Text(
        text = "ул. Пушкина дом Калатушкина",
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    )

    Spacer(modifier = Modifier.height(10.dp))

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
}

@Composable
fun HatchHorizontalDivider() {
    Row(
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        repeat(25) {
            Box(
                modifier = Modifier
                    .height(2.dp)
                    .width(10.dp)
                    .background(Color.LightGray)
            )
        }
    }
}