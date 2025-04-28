package com.example.afishaapp.ui.widget.row

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.afishaapp.app.utils.convertData.ConvertCountTitle
import com.example.afishaapp.data.module.comment.Comment
import com.example.afishaapp.ui.theme.DefaultPadding
import com.example.afishaapp.ui.widget.card.CommentCard

@Composable
fun CommentsRow(
    comments: List<Comment>,
    onClick: () -> Unit
) {
    val commentLazyState = rememberLazyListState()
    val commentSnapBehavior = rememberSnapFlingBehavior(commentLazyState, SnapPosition.Start)

    if (comments.isNotEmpty()) {
        SelectRow(
            text = ConvertCountTitle.convertCommentsCount(comments.size),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        ) {
            onClick.invoke()
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = DefaultPadding),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            state = commentLazyState,
            flingBehavior = commentSnapBehavior
        ) {
            items(comments) { comment ->
                CommentCard(comment)
            }
        }
    }
}