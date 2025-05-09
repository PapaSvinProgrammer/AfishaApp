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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.app.navigation.AboutAppRoute
import com.example.afishaapp.app.navigation.StartRoute
import com.example.afishaapp.ui.screen.dialog.ExitDialog
import com.example.afishaapp.ui.theme.acidFontFamily
import com.example.afishaapp.ui.widget.row.SelectRow
import com.example.afishaapp.ui.widget.text.TitleTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    var exitState by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { TitleTopBar(stringResource(R.string.profile_title)) },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.arrow_back_description)
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { exitState = true }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_exit),
                            contentDescription = stringResource(R.string.exit)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                painter = painterResource(R.drawable.ic_account_circle),
                contentDescription = stringResource(R.string.ic_account_circle_content_description),
                modifier = Modifier
                    .size(180.dp)
                    .padding(top = 50.dp)
            )

            Text(
                text = "Урайкин Роман",
                fontFamily = acidFontFamily,
                fontSize = 32.sp,
                modifier = Modifier.padding(top = 10.dp)
            )

            Text(
                text = "UraykinRab@yandex.ru",
                fontSize = 15.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 5.dp, bottom = 60.dp)
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
            ) {
                navController.navigate(AboutAppRoute)
            }
        }
    }


    if (exitState) {
        ExitDialog(
            onConfirmClick = {
                navController.navigate(StartRoute) {
                    popUpTo(navController.graph.id)
                }
            },
            onDismissClick = {
                exitState = false
            }
        )
    }
}