package com.example.afishaapp.app.navigation

import com.example.afishaapp.R

object NavBarItems {
    val barItems = listOf(
        BarItem(
            name = R.string.home_title_text,
            image = R.drawable.ic_home,
            imageFill = R.drawable.ic_home_fill,
            route = NavRoutes.HOME.name
        ),
        BarItem(
            name = R.string.ticket_title_text,
            image = R.drawable.ic_ticket,
            imageFill = R.drawable.ic_ticket_fill,
            route = NavRoutes.TICKETS.name
        ),
        BarItem(
            name = R.string.favorite_title_text,
            image = R.drawable.ic_favorite,
            imageFill = R.drawable.ic_favorite_fill,
            route = NavRoutes.FAVORITE.name
        ),
        BarItem(
            name = R.string.search_title_text,
            image = R.drawable.ic_search,
            imageFill = R.drawable.ic_search,
            route = NavRoutes.SEARCH.name
        )
    )
}