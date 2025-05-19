package com.example.afishaapp.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import com.example.afishaapp.app.App
import com.example.afishaapp.app.navigation.HomeRoute
import com.example.afishaapp.app.navigation.StartRoute
import com.example.afishaapp.di.viewModel.ViewModelFactory
import com.example.afishaapp.ui.screen.main.MainScreen
import com.example.afishaapp.ui.theme.AfishaAppTheme
import javax.inject.Inject

class MainActivity: ComponentActivity() {
    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var viewModel: MainViewModel

    @SuppressLint("ContextCastToActivity")
    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as App).appComponent.inject(this)

        viewModel.getTheme()
        viewModel.getEntryState()

        setContent {
            AfishaAppTheme(darkTheme = viewModel.isDarkTheme) {
                val context = LocalContext.current as ComponentActivity

                DisposableEffect(viewModel.isDarkTheme) {
                    context.enableEdgeToEdge(
                        statusBarStyle = getStatusBarStyle(viewModel.isDarkTheme)
                    )

                    onDispose { }
                }

                val startRoute = if (viewModel.isEntry) HomeRoute else StartRoute

                MainScreen(
                    viewModelFactory = viewModelFactory,
                    startRoute = startRoute
                )
            }
        }
    }

    private fun getStatusBarStyle(isDark: Boolean): SystemBarStyle {
        return when (isDark) {
            true -> SystemBarStyle.dark(Color.Transparent.toArgb())
            false -> SystemBarStyle.light(Color.Transparent.toArgb(), Color.White.toArgb())
        }
    }
}