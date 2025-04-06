package com.example.afishaapp.ui.widget.material

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.afishaapp.ui.theme.DefaultPadding
import kotlin.math.absoluteValue

fun PagerState.offsetForPage(page: Int) = currentPage - page + currentPageOffsetFraction

fun PagerState.indicatorOffsetForPage(page: Int) = 1f - offsetForPage(page).coerceIn(-1f, 1f).absoluteValue

@Composable
fun HorizontalCirclePagerIndicator(
    pagerState: PagerState
) {
    Row(
        Modifier.wrapContentHeight().fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray

            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clip(CircleShape)
                    .background(color)
                    .size(10.dp)
            )
        }
    }
}

@Composable
fun HorizontalLinePagerIndicator(
    modifier: Modifier = Modifier,
    state: PagerState
) {
    Row(
        modifier = modifier
            .padding(horizontal = DefaultPadding)
            .width(32.dp * state.pageCount)
            .height(48.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        repeat(state.pageCount) {
            val offset = state.indicatorOffsetForPage(it)

            Box(
                modifier = Modifier
                    .padding(horizontal = 2.dp)
                    .weight(.5f + offset * 3f)
                    .height(8.dp)
                    .background(
                        color = Color.Black,
                        shape = CircleShape
                    ),
               contentAlignment = Alignment.Center
            ) { }
        }
    }
}