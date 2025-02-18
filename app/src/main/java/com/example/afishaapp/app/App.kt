package com.example.afishaapp.app

import android.app.Application
import com.example.afishaapp.di.AppComponent
import com.example.afishaapp.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}