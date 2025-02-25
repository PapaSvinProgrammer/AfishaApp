package com.example.afishaapp.ui.screen.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.app.navigation.EntryRoute
import com.example.afishaapp.app.navigation.RegistrationRoute
import com.example.afishaapp.ui.theme.acidFontFamily

@Composable
fun StartScreen(
    navController: NavController,
    padding: PaddingValues
) {
    Image(
        bitmap = ImageBitmap.imageResource(R.drawable.start_image),
        contentDescription = "Start image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )

    Column(
        modifier = Modifier.padding(20.dp, padding.calculateTopPadding() + 60.dp)
    ) {
        Text(
            text = stringResource(R.string.music_text),
            color = Color.White,
            fontSize = 46.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = acidFontFamily
        )

        Text(
            text = stringResource(R.string.cinema_text),
            color = Color.White,
            fontSize = 46.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = acidFontFamily

        )

        Text(
            text = stringResource(R.string.theater_text),
            color = Color.White,
            fontSize = 46.sp,
            fontWeight = FontWeight.Medium,
            fontFamily = acidFontFamily
        )
    }

    Box(
        contentAlignment = Alignment.BottomCenter,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
        ) {
            Button(
                onClick = { navController.navigate(EntryRoute) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp, 30.dp, 50.dp, 10.dp),
                colors = ButtonDefaults.filledTonalButtonColors(containerColor = Color.White)
            ) {
                Text(
                    text = stringResource(R.string.entry_text),
                    color = Color.Black
                )
            }

            Button(
                onClick = { navController.navigate(RegistrationRoute) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(50.dp, 0.dp, 50.dp, 10.dp),
                colors = ButtonDefaults.filledTonalButtonColors(containerColor = Color.White)
            ) {
                Text(
                    text = stringResource(R.string.registration_text),
                    color = Color.Black
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 10.dp)
            ) {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "Войти с помощью",
                    color = Color.White,
                    modifier = Modifier.weight(2f),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )

                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.White,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(0.dp, 0.dp, 0.dp, padding.calculateBottomPadding() + 35.dp)
            ) {
                IconButton(
                    onClick = {  },
                    modifier = Modifier.padding(10.dp, 0.dp).size(45.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_vk),
                        contentDescription = "Vk",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(36.dp)
                    )
                }

                IconButton(
                    onClick = {  },
                    modifier = Modifier
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .size(36.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_google),
                        contentDescription = "Vk",
                        tint = Color.Unspecified
                    )
                }

                IconButton(
                    onClick = {  },
                    modifier = Modifier
                        .padding(15.dp, 0.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .background(Color.White)
                        .size(36.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_yandex),
                        contentDescription = "Vk",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}