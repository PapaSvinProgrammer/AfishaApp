package com.example.afishaapp.ui.screen.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.afishaapp.app.navigation.BottomNavigationBar
import com.example.afishaapp.app.navigation.FavoriteRoute
import com.example.afishaapp.app.navigation.HomeRoute
import com.example.afishaapp.app.navigation.NavRoute
import com.example.afishaapp.app.navigation.NavigationGraph
import com.example.afishaapp.app.navigation.SearchRoute
import com.example.afishaapp.app.navigation.TicketsRoute
import com.example.afishaapp.di.viewModel.ViewModelFactory

var bottomBarVisibilityState = mutableStateOf(false)

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun MainScreen(
    viewModelFactory: ViewModelFactory,
    startRoute: NavRoute
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    bottomBarIsVisibility(currentRoute)

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                visibilityState = bottomBarVisibilityState
            )
        }
    ) { innerPadding ->
        NavigationGraph(
            navController = navController,
            padding = innerPadding,
            viewModelFactory = viewModelFactory,
            startRoute = startRoute
        )
    }
}

private fun bottomBarIsVisibility(route: String?) {
    when (route) {
        HomeRoute::class.java.canonicalName -> bottomBarVisibilityState.value = true
        TicketsRoute::class.java.canonicalName -> bottomBarVisibilityState.value = true
        FavoriteRoute::class.java.canonicalName -> bottomBarVisibilityState.value = true
        SearchRoute::class.java.canonicalName -> bottomBarVisibilityState.value = true
        else -> bottomBarVisibilityState.value = false
    }
}