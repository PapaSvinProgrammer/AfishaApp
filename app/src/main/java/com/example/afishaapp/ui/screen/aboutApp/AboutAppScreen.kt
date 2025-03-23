package com.example.afishaapp.ui.screen.aboutApp

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.ui.widget.row.SelectRow
import com.example.afishaapp.ui.widget.text.TitleTopBar
import androidx.core.net.toUri

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutAppScreen(
    navController: NavController
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    TitleTopBar(stringResource(R.string.about_app))
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = stringResource(R.string.ic_arrow_back_content_description)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            SelectRow(
                text = "Условия использования Яндекс Карт"
            ) {
                openConditions(
                    url = "https://yandex.ru/legal/maps_api/",
                    context = context
                )
            }
        }
    }
}

private fun openConditions(url: String, context: Context) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        url.toUri()
    )

    context.startActivity(intent)
}