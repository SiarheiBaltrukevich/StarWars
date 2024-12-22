package com.fanatics.codechallenge.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fanatics.codechallenge.ui.screen.home.HomeScreen
import com.fanatics.codechallenge.ui.screen.person.PersonScreen

@Composable
fun AppNavigator(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.Home.name,
        modifier = modifier.fillMaxSize()
    ) {
        composable(route = AppScreens.Home.name) {
            HomeScreen(navController)
        }
        composable(route = AppScreens.Person.name) {
            PersonScreen(navController)
        }
    }
}
