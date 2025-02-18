package com.example.afishaapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import com.example.afishaapp.app.App
import com.example.afishaapp.di.viewModel.ViewModelFactory
import com.example.afishaapp.ui.screen.main.MainScreen
import javax.inject.Inject

class MainActivity: ComponentActivity() {
    @Inject lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).appComponent.inject(this)

        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                MainScreen(
                    viewModelFactory = viewModelFactory
                )
            }
        }
    }
}