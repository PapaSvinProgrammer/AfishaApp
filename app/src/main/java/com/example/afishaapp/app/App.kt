package com.example.afishaapp.app

import android.app.Application
import com.example.afishaapp.BuildConfig
import com.example.afishaapp.di.AppComponent
import com.example.afishaapp.di.DaggerAppComponent
import com.yandex.mapkit.MapKitFactory

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)
        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}