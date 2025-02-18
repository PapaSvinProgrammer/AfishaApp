package com.example.afishaapp.ui.screen.main

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.afishaapp.app.navigation.BottomNavigationBar
import com.example.afishaapp.app.navigation.NavRoutes
import com.example.afishaapp.app.navigation.NavigationGraph
import com.example.afishaapp.di.viewModel.ViewModelFactory

private val visibilityState = mutableStateOf(false)

@Composable
fun MainScreen(
    viewModelFactory: ViewModelFactory
) {
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
            padding = innerPadding,
            viewModelFactory = viewModelFactory
        )
    }
}

private fun bottomBarIsVisibility(route: String?) {
    when (route) {
        NavRoutes.HOME.name -> visibilityState.value = true
        NavRoutes.TICKETS.name -> visibilityState.value = true
        NavRoutes.FAVORITE.name -> visibilityState.value = true
        NavRoutes.SEARCH.name -> visibilityState.value = true
        else -> visibilityState.value = false
    }
}