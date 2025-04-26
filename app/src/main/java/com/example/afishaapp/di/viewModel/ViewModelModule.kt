package com.example.afishaapp.di.viewModel

import androidx.lifecycle.ViewModel
import com.example.afishaapp.ui.screen.aboutEvent.AboutEventViewModel
import com.example.afishaapp.ui.screen.aboutMovie.AboutMovieViewModel
import com.example.afishaapp.ui.screen.movieShowBottomSheet.MovieShowViewModel
import com.example.afishaapp.ui.screen.commentListEvent.CommentListEventViewModel
import com.example.afishaapp.ui.screen.commentListPlace.CommentListPlaceViewModel
import com.example.afishaapp.ui.screen.detailTicket.DetailTicketViewModel
import com.example.afishaapp.ui.screen.entry.EntryViewModel
import com.example.afishaapp.ui.screen.event.EventViewModel
import com.example.afishaapp.ui.screen.eventList.EventListViewModel
import com.example.afishaapp.ui.screen.formPaymentScreen.FormPaymentViewModel
import com.example.afishaapp.ui.screen.home.HomeViewModel
import com.example.afishaapp.ui.screen.map.MapViewModel
import com.example.afishaapp.ui.screen.movie.MovieViewModel
import com.example.afishaapp.ui.screen.movieList.MovieListViewModel
import com.example.afishaapp.ui.screen.place.PlaceViewModel
import com.example.afishaapp.ui.screen.registration.RegistrationViewModel
import com.example.afishaapp.ui.screen.start.StartViewModel
import com.example.afishaapp.ui.screen.ticket.TicketViewModel
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import kotlin.reflect.KClass

@Target(
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(EntryViewModel::class)
    fun bindEntryViewModel(viewModel: EntryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun bindRegistrationViewModel(viewModel: RegistrationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventListViewModel::class)
    fun bindEventListViewModel(viewModel: EventListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    fun bindMovieListViewModel(viewModel: MovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EventViewModel::class)
    fun bindEventViewModel(viewModel: EventViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CommentListEventViewModel::class)
    fun bindCommentLIstViewModel(viewModel: CommentListEventViewModel): ViewModel
    
    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    fun bindMovieViewModel(viewModel: MovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieShowViewModel::class)
    fun bindMovieShowViewModel(viewModel: MovieShowViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AboutEventViewModel::class)
    fun bindAboutEventViewModel(viewModel: AboutEventViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AboutMovieViewModel::class)
    fun bindAboutMovieViewModel(viewModel: AboutMovieViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MapViewModel::class)
    fun binMapViewModel(viewmodel: MapViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(StartViewModel::class)
    fun bindStartViewModel(viewModel: StartViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PlaceViewModel::class)
    fun bindPlaceViewModel(viewModel: PlaceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CommentListPlaceViewModel::class)
    fun bindCommentListPlaceViewModel(viewModel: CommentListPlaceViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FormPaymentViewModel::class)
    fun bindFormPaymentViewModel(viewModel: FormPaymentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TicketViewModel::class)
    fun bindTicketViewModel(viewModel: TicketViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailTicketViewModel::class)
    fun bindDetailTicketViewModel(viewModel: DetailTicketViewModel): ViewModel
}