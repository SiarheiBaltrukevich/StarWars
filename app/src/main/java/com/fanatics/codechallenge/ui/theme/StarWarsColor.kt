package com.fanatics.codechallenge.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Immutable
internal data class StarWarsColors(
    val home: HomeColors = HomeColors()
) {
    companion object {
        val darkSky = Color(0xff1E1266)
        val black = Color(0xff000000)
        val lightGray20 = Color(0x34E0E0E3)
        val lightGray = Color(0xFFE0E0E3)
        val gray = Color(0xFFBABABD)
    }
}

@Immutable
internal data class HomeColors(
    val header: Header = Header(),
    val gradientBackground: Brush = Brush.verticalGradient(
        colors = listOf(
            StarWarsColors.darkSky,
            StarWarsColors.black,
        )
    ),
    val gradientDivider: Brush = Brush.horizontalGradient(
        colors = listOf(
            StarWarsColors.lightGray20,
            StarWarsColors.lightGray,
            StarWarsColors.lightGray20,
        )
    ),

)

@Immutable
internal data class Header(
    val divider: Color = StarWarsColors.gray,
    val background: Color = StarWarsColors.darkSky,
    val fontColor: Color = StarWarsColors.lightGray
)
