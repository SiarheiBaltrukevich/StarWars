package com.fanatics.codechallenge.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fanatics.codechallenge.ui.screen.home.HomeScreen
import com.fanatics.codechallenge.ui.screen.person.PersonScreen
import com.fanatics.codechallenge.ui.theme.SWTheme

@Composable
fun AppNavigator(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.Home.name,
        modifier = modifier
            .fillMaxSize()
            .background(SWTheme.colors.common.gradientBackground)
    ) {
        composable(
            route = AppScreens.Home.name,
            enterTransition = slidingFadeInAnimation(direction = AnimationDirection.LtR),
            exitTransition = slidingFadeOutAnimation(direction = AnimationDirection.RtL)
        ) {
            HomeScreen(navController)
        }
        composable(
            route = AppScreens.Person.name,
            enterTransition = slidingFadeInAnimation(AnimationDirection.RtL),
            exitTransition = slidingFadeOutAnimation(AnimationDirection.LtR)
        ) {
            PersonScreen(navController)
        }
    }
}
