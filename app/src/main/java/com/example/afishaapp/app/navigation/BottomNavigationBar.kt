package com.example.afishaapp.app.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(
    navController: NavHostController
) {
    NavigationBar {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        NavBarItems.barItems.forEach { barItem ->
            NavigationBarItem(
                selected = currentRoute == barItem.route,
                onClick = {
                    navController.navigate(barItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    val iconId = if (currentRoute == barItem.route)
                        barItem.imageFill
                    else
                        barItem.image

                    Icon(
                        painter = painterResource(iconId),
                        contentDescription = stringResource(barItem.name)
                    )
                },
                label = { Text(text = stringResource(barItem.name)) }
            )
        }
    }
}