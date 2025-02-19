package com.example.afishaapp.di

import android.content.Context
import com.example.afishaapp.di.data.AuthModuleData
import com.example.afishaapp.di.data.CategoryModuleData
import com.example.afishaapp.di.data.PreferencesModuleData
import com.example.afishaapp.di.domain.AuthModuleDomain
import com.example.afishaapp.di.domain.CategoryModuleDomain
import com.example.afishaapp.di.domain.PreferencesModuleDomain
import com.example.afishaapp.di.domain.ValidModuleDomain
import com.example.afishaapp.ui.MainActivity
import com.example.afishaapp.di.viewModel.ViewModelFactoryModule
import com.example.afishaapp.di.viewModel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AuthModuleData::class,
        CategoryModuleData::class,
        PreferencesModuleData::class,

        AuthModuleDomain::class,
        CategoryModuleDomain::class,
        PreferencesModuleDomain::class,
        ValidModuleDomain::class,

        NetworkModule::class,
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