package com.fanatics.codechallenge.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

@Composable
fun StarWarsAppTheme(content: @Composable () -> Unit) {
    val starWarsDimens = StarWarsDimens()
    val starWarsColors = StarWarsColors()
    val starWarsTypography = StarWarsTypography()

    CompositionLocalProvider(
        LocalDimens provides starWarsDimens,
        LocalColors provides starWarsColors,
        LocalTypography provides starWarsTypography,
        content = content,
    )
}

internal object SWTheme {
    val dimens: StarWarsDimens
        @Composable
        get() = LocalDimens.current
    val typography: StarWarsTypography
        @Composable
        get() = LocalTypography.current
    val colors: StarWarsColors
        @Composable
        get() = LocalColors.current
}

private val LocalDimens = staticCompositionLocalOf<StarWarsDimens> {
    error("No dimens provided")
}
private val LocalColors = staticCompositionLocalOf<StarWarsColors> {
    error("No colors provided")
}
private val LocalTypography = staticCompositionLocalOf<StarWarsTypography> {
    error("No SliceTypography provided")
}
