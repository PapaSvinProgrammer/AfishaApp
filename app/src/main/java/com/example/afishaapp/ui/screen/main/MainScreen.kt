package com.example.afishaapp.ui.screen.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.afishaapp.app.navigation.BottomNavigationBar
import com.example.afishaapp.app.navigation.NavRoutes
import com.example.afishaapp.app.navigation.NavigationGraph

private val visibilityState = mutableStateOf(false)

@Preview(showSystemUi = true)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    bottomBarIsVisibility(currentRoute)

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                visibilityState = visibilityState
            )
        }
    ) { innerPadding ->
        NavigationGraph(
            navController = navController,
            padding = innerPadding
        )
    }
}

private fun bottomBarIsVisibility(route: String?) {
    when (route) {
        NavRoutes.START.name -> visibilityState.value = false
        NavRoutes.ENTRY.name -> visibilityState.value = false
        NavRoutes.REGISTRATION.name -> visibilityState.value = false
        NavRoutes.HOME.name -> visibilityState.value = true
        NavRoutes.TICKETS.name -> visibilityState.value = true
        NavRoutes.FAVORITE.name -> visibilityState.value = true
        NavRoutes.SEARCH.name -> visibilityState.value = true
    }
}