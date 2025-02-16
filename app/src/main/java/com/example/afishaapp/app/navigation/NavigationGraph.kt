package com.example.afishaapp.app.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.afishaapp.ui.screen.entry.EntryScreen
import com.example.afishaapp.ui.screen.favorite.FavoriteScreen
import com.example.afishaapp.ui.screen.home.HomeScreen
import com.example.afishaapp.ui.screen.registration.RegistrationScreen
import com.example.afishaapp.ui.screen.search.SearchScreen
import com.example.afishaapp.ui.screen.start.StartScreen
import com.example.afishaapp.ui.screen.ticket.TicketScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    padding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.START.name
    ) {
        composable(NavRoutes.HOME.name) { HomeScreen() }
        composable(NavRoutes.TICKETS.name) { TicketScreen() }
        composable(NavRoutes.FAVORITE.name) { FavoriteScreen() }
        composable(NavRoutes.SEARCH.name) { SearchScreen() }
        composable(NavRoutes.START.name) {
            StartScreen(
                navController = navController,
                padding = padding
            )
        }
        composable(NavRoutes.ENTRY.name) { EntryScreen(navController) }
        composable(NavRoutes.REGISTRATION.name) { RegistrationScreen(navController) }
    }
}