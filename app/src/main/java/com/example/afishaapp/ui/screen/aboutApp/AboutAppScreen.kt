package com.example.afishaapp.ui.screen.aboutApp

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.afishaapp.R
import com.example.afishaapp.ui.widget.row.SelectRow
import com.example.afishaapp.ui.widget.text.TitleTopBar
import androidx.core.net.toUri
import com.example.afishaapp.ui.theme.DefaultPadding

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
                            contentDescription = stringResource(R.string.arrow_back_description)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = DefaultPadding),
                text = stringResource(R.string.terms_of_use),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            LazyColumn(
                modifier = Modifier.padding(top = 5.dp)
            ) {
                items(arrayTermsOfAgreement) { pair ->
                    SelectRow(
                        text = pair.first
                    ) {
                        openConditions(
                            url = pair.second,
                            context = context
                        )
                    }
                }
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