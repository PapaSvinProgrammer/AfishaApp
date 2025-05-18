package com.example.afishaapp.ui.screen.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.app.navigation.AboutAppRoute
import com.example.afishaapp.app.navigation.ChartRoute
import com.example.afishaapp.app.navigation.StartRoute
import com.example.afishaapp.app.navigation.StartSettingRoute
import com.example.afishaapp.domain.module.AppTheme
import com.example.afishaapp.ui.screen.dialog.ExitDialog
import com.example.afishaapp.ui.theme.acidFontFamily
import com.example.afishaapp.ui.widget.row.CheckRow
import com.example.afishaapp.ui.widget.row.SelectRow
import com.example.afishaapp.ui.widget.text.TitleTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: SettingsViewModel
) {
    LifecycleEventEffect(Lifecycle.Event.ON_START) {
        viewModel.getDarkTheme()
    }

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
                        onClick = { viewModel.updateExitState(true) }
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
            AccountInfoContent()

            SelectRow(text = stringResource(R.string.clarify_preferences)) {
                navController.navigate(StartSettingRoute)
            }

            SelectRow(text = stringResource(R.string.theme)) {
                viewModel.updateChangeThemeState(true)
            }

            SelectRow(text = stringResource(R.string.statistics)) {
                navController.navigate(ChartRoute)
            }

            SelectRow(text = stringResource(R.string.support)) { }

            SelectRow(text = stringResource(R.string.about_app)) {
                navController.navigate(AboutAppRoute)
            }
        }
    }

    if (viewModel.exitState) {
        ExitDialog(
            onConfirmClick = {
                navController.navigate(StartRoute) {
                    popUpTo(navController.graph.id)
                }
            },
            onDismissClick = {
                viewModel.updateExitState(false)
            }
        )
    }

    if (viewModel.changeThemeState) {
        ChangeThemeDialog(
            isDark = viewModel.isDark,
            onChange = { viewModel.changeTheme(it) },
            onDismiss = { viewModel.updateChangeThemeState(false) }
        )
    }
}

@Composable
private fun AccountInfoContent() {
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
}

@Composable
fun ChangeThemeDialog(
    isDark: Boolean,
    onChange: (AppTheme) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        title = { Text(text = stringResource(R.string.select_theme)) },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                CheckRow(
                    text = stringResource(R.string.light),
                    isCheck = !isDark
                ) { onChange(AppTheme.LIGHT) }

                CheckRow(
                    text = stringResource(R.string.dark),
                    isCheck = isDark
                ) { onChange(AppTheme.DARK) }
            }
        },
        confirmButton = {},
        dismissButton = {},
        onDismissRequest = onDismiss
    )
}