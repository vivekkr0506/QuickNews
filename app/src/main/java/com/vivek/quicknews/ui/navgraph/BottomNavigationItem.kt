package com.vivek.quicknews.ui.navgraph

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screens(val route: String) {
    object Home : Screens("home_route")
    object Search : Screens("search_route")
    object Profile : Screens("bookmark_route")
    object NewsDetails : Screens("news_details")
}

data class BottomNavigationItem(
    val label: String = "", val icon: ImageVector = Icons.Filled.Home, val route: String = ""
) {

    //function to get the list of bottomNavigationItems
    fun bottomNavigationItems(): List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home", icon = Icons.Filled.Home, route = Screens.Home.route
            ),
            BottomNavigationItem(
                label = "Search", icon = Icons.Filled.Search, route = Screens.Search.route
            ),
            BottomNavigationItem(
                label = "Bookmarks", icon = Icons.Filled.Star, route = Screens.Profile.route
            ),
        )
    }
}

