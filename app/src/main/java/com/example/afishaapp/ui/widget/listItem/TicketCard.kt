package com.example.afishaapp.ui.widget.listItem

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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.data.room.ticket.TicketEntity
import com.example.afishaapp.ui.screen.ticketDetail.drawTicketView
import com.example.afishaapp.ui.widget.row.SubwayRow

@Composable
fun TicketCard(
    ticket: TicketEntity,
    onClick: (TicketEntity) -> Unit
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
            onClick = { onClick.invoke(ticket) },
            modifier = Modifier.fillMaxSize().padding(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
            )
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    TopContent(ticket)
                }

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Spacer(modifier = Modifier.height(25.dp))
                    BottomContent(ticket)
                }
            }
        }

        HatchHorizontalDivider()
    }
}

@Composable
private fun TopContent(ticket: TicketEntity) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = ticket.name,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(4f),
            color = MaterialTheme.colorScheme.onSurface
        )

        Text(
            text = ticket.age,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f),
            color = MaterialTheme.colorScheme.onSurface
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
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Box(
            modifier = Modifier
                .size(7.dp)
                .clip(CircleShape)
                .background(color = MaterialTheme.colorScheme.onSurfaceVariant)
        )

        Text(
            text = "23 февраля",
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,

            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun BottomContent(ticket: TicketEntity) {
    Text(
        text = ticket.address,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )

    Spacer(modifier = Modifier.height(10.dp))

    SubwayRow(
        location = ticket.location,
        subway = ticket.subway
    )
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
                    .background(MaterialTheme.colorScheme.onSurfaceVariant)
            )
        }
    }
}