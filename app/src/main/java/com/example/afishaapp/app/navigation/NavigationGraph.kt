package com.example.afishaapp.app.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.afishaapp.di.viewModel.ViewModelFactory
import com.example.afishaapp.ui.screen.aboutApp.AboutAppScreen
import com.example.afishaapp.ui.screen.aboutEvent.AboutEventScreen
import com.example.afishaapp.ui.screen.aboutEvent.AboutEventViewModel
import com.example.afishaapp.ui.screen.aboutMovie.AboutMovieScreen
import com.example.afishaapp.ui.screen.aboutMovie.AboutMovieViewModel
import com.example.afishaapp.ui.screen.chart.ChartScreen
import com.example.afishaapp.ui.screen.chart.ChartViewModel
import com.example.afishaapp.ui.screen.commentListEvent.CommentListEventScreen
import com.example.afishaapp.ui.screen.commentListEvent.CommentListEventViewModel
import com.example.afishaapp.ui.screen.commentListPlace.CommentListPlaceScreen
import com.example.afishaapp.ui.screen.commentListPlace.CommentListPlaceViewModel
import com.example.afishaapp.ui.screen.ticketDetail.DetailTicketScreen
import com.example.afishaapp.ui.screen.ticketDetail.DetailTicketViewModel
import com.example.afishaapp.ui.screen.entry.EntryScreen
import com.example.afishaapp.ui.screen.entry.EntryViewModel
import com.example.afishaapp.ui.screen.event.EventScreen
import com.example.afishaapp.ui.screen.event.EventViewModel
import com.example.afishaapp.ui.screen.eventList.EventListScreen
import com.example.afishaapp.ui.screen.eventList.EventListViewModel
import com.example.afishaapp.ui.screen.favorite.FavoriteScreen
import com.example.afishaapp.ui.screen.favorite.FavoriteViewModel
import com.example.afishaapp.ui.screen.formPaymentScreen.FormPaymentScreen
import com.example.afishaapp.ui.screen.formPaymentScreen.FormPaymentViewModel
import com.example.afishaapp.ui.screen.home.HomeScreen
import com.example.afishaapp.ui.screen.home.HomeViewModel
import com.example.afishaapp.ui.screen.map.MapScreen
import com.example.afishaapp.ui.screen.map.MapViewModel
import com.example.afishaapp.ui.screen.movie.MovieScreen
import com.example.afishaapp.ui.screen.movie.MovieViewModel
import com.example.afishaapp.ui.screen.movieList.MovieListScreen
import com.example.afishaapp.ui.screen.movieList.MovieListViewModel
import com.example.afishaapp.ui.screen.place.PlaceScreen
import com.example.afishaapp.ui.screen.place.PlaceViewModel
import com.example.afishaapp.ui.screen.settings.ProfileScreen
import com.example.afishaapp.ui.screen.registration.RegistrationScreen
import com.example.afishaapp.ui.screen.registration.RegistrationViewModel
import com.example.afishaapp.ui.screen.search.SearchScreen
import com.example.afishaapp.ui.screen.search.SearchViewModel
import com.example.afishaapp.ui.screen.settings.SettingsViewModel
import com.example.afishaapp.ui.screen.start.StartScreen
import com.example.afishaapp.ui.screen.start.StartViewModel
import com.example.afishaapp.ui.screen.startSetting.StartSettingScreen
import com.example.afishaapp.ui.screen.startSetting.StartSettingViewModel
import com.example.afishaapp.ui.screen.ticket.TicketScreen
import com.example.afishaapp.ui.screen.ticket.TicketViewModel

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
@Composable
fun NavigationGraph(
    navController: NavHostController,
    padding: PaddingValues,
    viewModelFactory: ViewModelFactory
) {
    NavHost(
        navController = navController,
        startDestination = ChartRoute,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(300)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(300)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(300)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(300)
            )
        }
    ) {
        composable<HomeRoute> {
            val viewModel: HomeViewModel = viewModel(factory = viewModelFactory)

            HomeScreen(
                navController = navController,
                viewModel = viewModel,
                innerPadding = padding
            )
        }

        composable<TicketsRoute> {
            val viewModel: TicketViewModel = viewModel(factory = viewModelFactory)

            TicketScreen(
                navController = navController,
                paddingValues = padding,
                viewModel = viewModel
            )
        }

        composable<FavoriteRoute> {
            val viewModel: FavoriteViewModel = viewModel(factory = viewModelFactory)

            FavoriteScreen(
                navController = navController,
                viewModel = viewModel,
                innerPadding = padding
            )
        }

        composable<SearchRoute> {
            val viewMode: SearchViewModel = viewModel(factory = viewModelFactory)

            SearchScreen(
                navController = navController,
                viewModel = viewMode,
                padding = padding
            )
        }

        composable<StartRoute> {
            val viewModel: StartViewModel = viewModel(factory = viewModelFactory)
            StartScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable<EntryRoute>  {
            val viewModel: EntryViewModel = viewModel(factory = viewModelFactory)
            EntryScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable<RegistrationRoute> {
            val viewModel: RegistrationViewModel = viewModel(factory = viewModelFactory)
            RegistrationScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable<ProfileRoute> {
            val viewModel: SettingsViewModel = viewModel(factory = viewModelFactory)
            ProfileScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable<MovieListRoute> {
            val movieListRoute = it.toRoute<MovieListRoute>()
            val viewModel: MovieListViewModel = viewModel(factory = viewModelFactory)

            MovieListScreen(
                viewModel = viewModel,
                navController = navController,
                topTitle = movieListRoute.title,
                location = movieListRoute.location
            )
        }

        composable<EventListRoute> {
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

        composable<EventRoute> {
            val eventRoute = it.toRoute<EventRoute>()
            val viewModel: EventViewModel = viewModel(factory = viewModelFactory)

            EventScreen(
                navController = navController,
                viewModel = viewModel,
                eventId = eventRoute.eventId
            )
        }

        composable<CommentListEventRoute> {
            val commentListEventRoute = it.toRoute<CommentListEventRoute>()
            val viewModel: CommentListEventViewModel = viewModel(factory = viewModelFactory)

            CommentListEventScreen(
                navController = navController,
                viewModel = viewModel,
                eventId = commentListEventRoute.id,
                eventName = commentListEventRoute.name
            )
        }

        composable<CommentListPlaceRoute> {
            val route = it.toRoute<CommentListPlaceRoute>()
            val viewModel: CommentListPlaceViewModel = viewModel(factory = viewModelFactory)

            CommentListPlaceScreen(
                navController = navController,
                viewModel = viewModel,
                placeName = route.name,
                placeId = route.id
            )
        }

        composable<MovieRoute> {
            val movieRoute = it.toRoute<MovieRoute>()
            val viewModel: MovieViewModel = viewModel(factory = viewModelFactory)

            MovieScreen(
                navController = navController,
                viewModel = viewModel,
                movieId = movieRoute.id,
                viewModelFactory = viewModelFactory
            )
        }

        composable<AboutEventRoute> {
            val route = it.toRoute<AboutEventRoute>()
            val viewModel: AboutEventViewModel = viewModel(factory = viewModelFactory)

            AboutEventScreen(
                id = route.id,
                viewModel = viewModel,
                navController = navController
            )
        }

        composable<AboutMovieRoute> {
            val route = it.toRoute<AboutMovieRoute>()
            val viewModel: AboutMovieViewModel = viewModel(factory = viewModelFactory)

            AboutMovieScreen(
                viewModel = viewModel,
                navController = navController,
                id = route.id
            )
        }

        composable<AboutAppRoute> { AboutAppScreen(navController) }

        composable<MapRoute> {
            val route = it.toRoute<MapRoute>()
            val viewModel: MapViewModel = viewModel(factory = viewModelFactory)

            MapScreen(
                navController = navController,
                viewModel = viewModel,
                lat = route.lat,
                lon = route.lon,
                placeId = route.placeId
            )
        }

        composable<PlaceRoute> {
            val route = it.toRoute<PlaceRoute>()
            val viewModel: PlaceViewModel = viewModel(factory = viewModelFactory)

            PlaceScreen(
                navController = navController,
                viewModel = viewModel,
                placeId = route.placeId
            )
        }

        composable<FormPaymentRoute> {
            val viewModel: FormPaymentViewModel = viewModel(factory = viewModelFactory)
            val route = it.toRoute<FormPaymentRoute>()

            FormPaymentScreen(
                navController = navController,
                viewModel = viewModel,
                eventId = route.eventId
            )
        }

        composable<DetailTicketRoute>(
            enterTransition = { scaleIn(tween(300)) },
            exitTransition = { scaleOut(tween(300)) },
            popEnterTransition = { scaleIn(tween(300)) },
            popExitTransition = { scaleOut(tween(300)) + fadeOut(tween(300)) }
        ) {
            val viewModel: DetailTicketViewModel = viewModel(factory = viewModelFactory)
            val route = it.toRoute<DetailTicketRoute>()

            DetailTicketScreen(
                navController = navController,
                viewModel = viewModel,
                eventId = route.eventId
            )
        }

        composable<StartSettingRoute> {
            val viewModel: StartSettingViewModel = viewModel(factory = viewModelFactory)

            StartSettingScreen(
                navController = navController,
                viewModel = viewModel
            )
        }

        composable<ChartRoute> {
            val viewModel: ChartViewModel = viewModel(factory = viewModelFactory)

            ChartScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}