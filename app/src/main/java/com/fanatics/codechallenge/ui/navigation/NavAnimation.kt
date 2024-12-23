package com.fanatics.codechallenge.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavBackStackEntry

private const val PERSON_SCREEN_ANIMATION_APPEARANCE_TIME = 300

enum class AnimationDirection {
    LtR,
    RtL,
}

fun slidingFadeInAnimation(
    direction: AnimationDirection
): AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition = {
    slideInHorizontally(
        initialOffsetX = { if (direction == AnimationDirection.RtL) it else -it },
        animationSpec = tween(
            durationMillis = PERSON_SCREEN_ANIMATION_APPEARANCE_TIME,
            easing = LinearOutSlowInEasing
        )
    ) + fadeIn(
        animationSpec = tween(
            durationMillis = PERSON_SCREEN_ANIMATION_APPEARANCE_TIME,
            easing = LinearEasing
        )
    )
}

fun slidingFadeOutAnimation(
    direction: AnimationDirection
): AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition = {
    slideOutHorizontally(
        targetOffsetX = { if (direction == AnimationDirection.RtL) -it else it },
        animationSpec = tween(
            durationMillis = PERSON_SCREEN_ANIMATION_APPEARANCE_TIME,
            easing = LinearOutSlowInEasing
        )
    ) + fadeOut(
        animationSpec = tween(
            durationMillis = PERSON_SCREEN_ANIMATION_APPEARANCE_TIME,
            easing = LinearOutSlowInEasing
        )
    )
}
