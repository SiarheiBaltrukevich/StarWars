package com.fanatics.codechallenge.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Immutable
internal data class StarWarsTypography(
    val home: HomeTypography = HomeTypography(),
    val person: PersonTypography = PersonTypography(),
)

@Immutable
internal data class HomeTypography(
    val info: TextStyle = default18Light,
    val header: HeaderTypography = HeaderTypography(main = default26Light),
    val person: PersonItemTypography = PersonItemTypography()
)

@Immutable
internal data class PersonTypography(
    val info: TextStyle = default20Light,
    val header: HeaderTypography = HeaderTypography(main = default20Light),
    val dialog: PersonDialogTypography = PersonDialogTypography(),
)

@Immutable
internal data class PersonDialogTypography(
    val info: TextStyle = default15Dark,
    val header: TextStyle = default32Dark,
)

@Immutable
internal data class HeaderTypography(
    val main: TextStyle,
)

@Immutable
internal data class PersonItemTypography(
    val name: TextStyle = default18Light,
    val info: TextStyle = default15Light
)

private val default26Light = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 26.sp,
    lineHeight = 32.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.02.em,
    color = StarWarsColors.lightGray
)

private val default18Light = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 18.sp,
    lineHeight = 24.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.02.em,
    color = StarWarsColors.lightGray
)

private val default20Light = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 20.sp,
    lineHeight = 26.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.02.em,
    color = StarWarsColors.lightGray
)

private val default15Light = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 15.sp,
    lineHeight = 20.sp,
    fontWeight = FontWeight.Normal,
    color = StarWarsColors.lightGray,
)

private val default15Dark = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 15.sp,
    lineHeight = 20.sp,
    fontWeight = FontWeight.Normal,
    color = StarWarsColors.darkGray,
)

private val default32Dark = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 32.sp,
    lineHeight = 40.sp,
    fontWeight = FontWeight.Normal,
    color = StarWarsColors.darkGray,
)
