package com.example.afishaapp.ui

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import com.example.afishaapp.app.App
import com.example.afishaapp.di.viewModel.ViewModelFactory
import com.example.afishaapp.ui.screen.main.MainScreen
import com.example.afishaapp.ui.theme.AfishaAppTheme
import javax.inject.Inject

class MainActivity: ComponentActivity() {
    @Inject lateinit var viewModelFactory: ViewModelFactory

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).appComponent.inject(this)

        enableEdgeToEdge()
        setContent {
            AfishaAppTheme {
                MainScreen(
                    viewModelFactory = viewModelFactory
                )
            }
        }
    }
}