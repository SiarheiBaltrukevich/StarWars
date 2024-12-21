package com.fanatics.codechallenge.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Immutable
internal data class StarWarsColors(
    val common: CommonColors = CommonColors(),
    val home: HomeColors = HomeColors(),
    val person: PersonColors = PersonColors(),
) {
    companion object {
        val darkSky = Color(0xff1E1266)
        val black = Color(0xff000000)
        val black25 = Color(0x40000000)
        val lightGray5 = Color(0x0CE0E0E3)
        val lightGray = Color(0xFFE0E0E3)
        val gray = Color(0xFFBABABD)
        val darkGray = Color(0xFF151515)
        val gray40 = Color(0x65BABABD)
        val blueLink = Color(0xFF0A0AF3)
    }
}

@Immutable
internal data class CommonColors(
    val gradientBackground: Brush = Brush.verticalGradient(
        colors = listOf(
            StarWarsColors.darkSky,
            StarWarsColors.black,
        )
    ),
)

@Immutable
internal data class PersonColors(
    val icon: Color = StarWarsColors.lightGray,
    val link: Color = StarWarsColors.blueLink,
    val dialogBackground: Color = StarWarsColors.lightGray,
)

@Immutable
internal data class HomeColors(
    val header: HeaderColors = HeaderColors(),
    val personItem: PersonItemColors = PersonItemColors(),
    val gradientDivider: Brush = Brush. horizontalGradient(
        0.0f to StarWarsColors.lightGray5,
        0.05f to StarWarsColors.gray,
        0.95f to StarWarsColors.gray,
        1.0f to StarWarsColors.lightGray5,
    )
)

@Immutable
internal data class HeaderColors(
    val divider: Color = StarWarsColors.gray40,
    val background: Color = StarWarsColors.darkSky,
    val fontColor: Color = StarWarsColors.lightGray
)

@Immutable
internal data class PersonItemColors(
    val textColor: Color = StarWarsColors.lightGray,
    val iconColor: Color = StarWarsColors.lightGray,
    val background: Color = StarWarsColors.black25,
)
