package com.example.afishaapp.ui.widget.listItem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.afishaapp.app.utils.convertData.ConvertDate
import com.example.afishaapp.app.utils.generateAccountImage
import com.example.afishaapp.data.module.comment.Comment
import com.example.afishaapp.ui.theme.LightGray

@Composable
fun CommentCard(
    comment: Comment
) {
    Column(
        modifier = Modifier
            .width(300.dp)
            .height(175.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(LightGray)
            .padding(10.dp, 10.dp, 10.dp, 0.dp)

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = comment.user.avatar,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                error = painterResource(generateAccountImage()),
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )

            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = comment.user.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )

                Text(
                    text = ConvertDate.convertDatePosted(comment.datePosted.toLong()),
                    fontSize = 14.sp
                )
            }
        }

        Text(
            text = comment.text,
            maxLines = 4,
            fontSize = 15.sp,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}