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
import androidx.navigation.toRoute
import com.example.afishaapp.di.viewModel.ViewModelFactory
import com.example.afishaapp.ui.screen.commentList.CommentListScreen
import com.example.afishaapp.ui.screen.commentList.CommentListViewModel
import com.example.afishaapp.ui.screen.entry.EntryScreen
import com.example.afishaapp.ui.screen.entry.EntryViewModel
import com.example.afishaapp.ui.screen.event.EventScreen
import com.example.afishaapp.ui.screen.event.EventViewModel
import com.example.afishaapp.ui.screen.eventList.EventListScreen
import com.example.afishaapp.ui.screen.eventList.EventListViewModel
import com.example.afishaapp.ui.screen.favorite.FavoriteScreen
import com.example.afishaapp.ui.screen.home.HomeScreen
import com.example.afishaapp.ui.screen.home.HomeViewModel
import com.example.afishaapp.ui.screen.movie.MovieScreen
import com.example.afishaapp.ui.screen.movie.MovieViewModel
import com.example.afishaapp.ui.screen.movieList.MovieListScreen
import com.example.afishaapp.ui.screen.movieList.MovieListViewModel
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
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            val viewModel: HomeViewModel = viewModel(factory = viewModelFactory)

            HomeScreen(
                navController = navController,
                viewModel = viewModel,
                innerPadding = padding
            )
        }

        composable<TicketsRoute> { TicketScreen() }

        composable<FavoriteRoute> { FavoriteScreen() }

        composable<SearchRoute> { SearchScreen() }

        composable<StartRoute> {
            StartScreen(
                navController = navController,
                padding = padding
            )
        }

        composable<EntryRoute> (
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

        composable<RegistrationRoute> (
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

        composable<AccountRoute> (
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

        composable<MovieListRoute> (
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
            val movieListRoute = it.toRoute<MovieListRoute>()
            val viewModel: MovieListViewModel = viewModel(factory = viewModelFactory)

            MovieListScreen(
                viewModel = viewModel,
                navController = navController,
                topTitle = movieListRoute.title,
                location = movieListRoute.location
            )
        }

        composable<EventListRoute> (
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
            val eventListRoute = it.toRoute<EventListRoute>()
            val viewModel: EventListViewModel = viewModel(factory = viewModelFactory)

            EventListScreen(
                viewModel = viewModel,
                navController = navController,
                topTitle = eventListRoute.title,
                categorySlug = eventListRoute.categorySlug,
                location = eventListRoute.location
            )
        }

        composable<EventRoute> (
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
            val eventRoute = it.toRoute<EventRoute>()
            val viewModel: EventViewModel = viewModel(factory = viewModelFactory)

            EventScreen(
                navController = navController,
                viewModel = viewModel,
                eventId = eventRoute.eventId
            )
        }

        composable<CommentListRoute> (
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
            val commentListRoute = it.toRoute<CommentListRoute>()
            val viewModel: CommentListViewModel = viewModel(factory = viewModelFactory)

            CommentListScreen(
                navController = navController,
                viewModel = viewModel,
                objectId = commentListRoute.id,
                objectName = commentListRoute.name,
                type = commentListRoute.type
            )
        }

        composable<MovieRoute> (
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
            val movieRoute = it.toRoute<MovieRoute>()
            val viewModel: MovieViewModel = viewModel(factory = viewModelFactory)

            MovieScreen(
                navController = navController,
                viewModel = viewModel,
                movieId = movieRoute.id
            )
        }
    }
}