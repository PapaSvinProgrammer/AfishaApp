package com.example.afishaapp.di

import android.content.Context
import com.example.afishaapp.ui.MainActivity
import com.example.afishaapp.di.viewModel.ViewModelFactoryModule
import com.example.afishaapp.di.viewModel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(mainActivity: MainActivity)
}