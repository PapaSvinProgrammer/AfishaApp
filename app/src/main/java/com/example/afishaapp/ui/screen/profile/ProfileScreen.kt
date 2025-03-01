package com.example.afishaapp.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.app.navigation.StartRoute
import com.example.afishaapp.ui.theme.acidFontFamily
import com.example.afishaapp.ui.widget.SelectRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(
            title = { Text(text = stringResource(R.string.profile_title)) },
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Default.ArrowBack,
                        contentDescription = stringResource(R.string.ic_arrow_back_content_description)
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = {
                        navController.navigate(StartRoute) {
                            popUpTo(navController.graph.id)
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_exit),
                        contentDescription = stringResource(R.string.exit)
                    )
                }
            }
        )

        Icon(
            painter = painterResource(R.drawable.ic_account_circle),
            contentDescription = stringResource(R.string.ic_account_circle_content_description),
            modifier = Modifier
                .size(180.dp)
                .padding(0.dp, 50.dp, 0.dp, 0.dp)
        )

        Text(
            text = "Урайкин Роман",
            fontFamily = acidFontFamily,
            fontSize = 32.sp,
            modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 0.dp)
        )

        Text(
            text = "UraykinRab@yandex.ru",
            fontSize = 15.sp,
            color = Color.Gray,
            modifier = Modifier.padding(0.dp, 5.dp, 0.dp, 60.dp)
        )

        SelectRow(
            text = stringResource(R.string.clarify_preferences)
        ) { }

        SelectRow(
            text = stringResource(R.string.settings)
        ) { }

        SelectRow(
            text = stringResource(R.string.statistics)
        ) { }

        SelectRow(
            text = stringResource(R.string.support)
        ) { }

        SelectRow(
            text = stringResource(R.string.about_app)
        ) { }
    }
}