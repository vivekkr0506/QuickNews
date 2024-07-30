package com.vivek.quicknews.ui.navgraph

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vivek.quicknews.ui.screens.BookmarksScreen
import com.vivek.quicknews.ui.screens.HomeScreen
import com.vivek.quicknews.ui.screens.SearchScreen

@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()
    var navigationSelectedItem by remember { mutableStateOf(0) }
    Scaffold(modifier = Modifier.fillMaxSize(), bottomBar = {
        NavigationBar {
            BottomNavigationItem().bottomNavigationItems().forEachIndexed { index, navigationItem ->
                NavigationBarItem(selected = index == navigationSelectedItem, label = {
                    Text(navigationItem.label)
                }, icon = {
                    Icon(
                        navigationItem.icon, contentDescription = navigationItem.label
                    )
                }, onClick = {
                    navigationSelectedItem = index
                    navController.navigate(navigationItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
            }
        }
    }) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screens.Home.route,
            modifier = Modifier.padding(paddingValues = paddingValues)
        ) {
            composable(Screens.Home.route) {

                HomeScreen(
                    navController
                )
            }
            composable(Screens.Search.route) {
                SearchScreen(
                    navController
                )
            }
            composable(Screens.Profile.route) {
                BookmarksScreen(
                    navController
                )
            }
        }
    }
}