package com.example.afishaapp.ui.screen.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.afishaapp.app.navigation.BottomNavigationBar
import com.example.afishaapp.app.navigation.NavigationGraph

@Preview(showSystemUi = true)
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        NavigationGraph(
            navController = navController,
            padding = innerPadding
        )
    }
}