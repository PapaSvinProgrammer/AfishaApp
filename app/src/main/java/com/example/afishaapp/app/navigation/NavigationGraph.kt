package com.example.afishaapp.app.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.afishaapp.di.viewModel.ViewModelFactory
import com.example.afishaapp.ui.screen.entry.EntryScreen
import com.example.afishaapp.ui.screen.entry.EntryViewModel
import com.example.afishaapp.ui.screen.favorite.FavoriteScreen
import com.example.afishaapp.ui.screen.home.HomeScreen
import com.example.afishaapp.ui.screen.home.HomeViewModel
import com.example.afishaapp.ui.screen.profile.ProfileScreen
import com.example.afishaapp.ui.screen.registration.RegistrationScreen
import com.example.afishaapp.ui.screen.registration.RegistrationViewModel
import com.example.afishaapp.ui.screen.search.SearchScreen
import com.example.afishaapp.ui.screen.start.StartScreen
import com.example.afishaapp.ui.screen.ticket.TicketScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    padding: PaddingValues,
    viewModelFactory: ViewModelFactory
) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.HOME.name
    ) {
        composable(NavRoutes.HOME.name) {
            val viewModel: HomeViewModel = viewModel(factory = viewModelFactory)

            HomeScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(NavRoutes.TICKETS.name) { TicketScreen() }

        composable(NavRoutes.FAVORITE.name) { FavoriteScreen() }

        composable(NavRoutes.SEARCH.name) { SearchScreen() }

        composable(NavRoutes.START.name) {
            StartScreen(
                navController = navController,
                padding = padding
            )
        }

        composable(
            route = NavRoutes.ENTRY.name,
            enterTransition = {
                slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            val viewModel: EntryViewModel = viewModel(factory = viewModelFactory)
            EntryScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable(
            route = NavRoutes.REGISTRATION.name,
            enterTransition = {
                slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            val viewModel: RegistrationViewModel = viewModel(factory = viewModelFactory)
            RegistrationScreen(
               navController =  navController,
                viewModel = viewModel
            )
        }

        composable(
            route = NavRoutes.ACCOUNT.name,
            enterTransition = {
                slideIntoContainer(
                    animationSpec = tween(300, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    animationSpec = tween(300, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            ProfileScreen(navController)
        }
    }
}