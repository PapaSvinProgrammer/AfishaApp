package com.example.afishaapp.ui.screen.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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

@Composable
fun StartScreen(navController: NavController) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.start_poster),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(400.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)
            ) {
                Button(
                    onClick = { navController.navigate(EntryRoute) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp, 30.dp, 40.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.entry_text),
                        color = Color.White
                    )
                }

                Button(
                    onClick = { navController.navigate(RegistrationRoute) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp, 0.dp, 40.dp, 10.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.registration_text),
                        color = Color.White
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 10.dp)
                ) {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.Gray,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "Войти с помощью",
                        color = Color.Gray,
                        modifier = Modifier.weight(2f),
                        textAlign = TextAlign.Center,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )

                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.Gray,
                        modifier = Modifier.weight(1f)
                    )
                }

                SupportRegistrationRow(innerPadding)
            }
        }
    }
}

@Composable
private fun SupportRegistrationRow(padding: PaddingValues) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        modifier = Modifier.padding(bottom = padding.calculateBottomPadding() + 35.dp)
    ) {
        IconButton(
            onClick = {  },
            modifier = Modifier.padding(vertical = 10.dp).size(45.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_vk),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(34.dp)
            )
        }

        IconButton(
            onClick = {  },
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .background(Color.White)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_google),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(34.dp)
            )
        }

        IconButton(
            onClick = {  },
            modifier = Modifier
                .clip(shape = RoundedCornerShape(8.dp))
                .background(Color.White)

        ) {
            Icon(
                painter = painterResource(R.drawable.ic_yandex),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(37.dp)
            )
        }
    }
}