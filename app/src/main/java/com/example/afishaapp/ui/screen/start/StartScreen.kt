package com.example.afishaapp.ui.screen.start

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.afishaapp.app.navigation.NavRoutes

@Composable
fun StartScreen(
    navHostController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { navHostController.navigate(NavRoutes.ENTRY.name) }
        ) {
            Text(text = "Вход")
        }

        Button(
            onClick = { navHostController.navigate(NavRoutes.REGISTRATION.name) }
        ) {
            Text(text = "Регистрация")
        }
    }
}