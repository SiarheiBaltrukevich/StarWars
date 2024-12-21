package com.fanatics.codechallenge.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import org.w3c.dom.Text

@Immutable
data class StarWarsTypography(
    val home: HomeTypography = HomeTypography(),
)

@Immutable
data class HomeTypography(
    val header: HomeHeader = HomeHeader(),
    val person: PersonItem = PersonItem()
)

@Immutable
data class HomeHeader(
    val main: TextStyle = default26
)

@Immutable
data class PersonItem(
    val name: TextStyle = default18,
    val info: TextStyle = default13
)

private val default26 = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 26.sp,
    lineHeight = 32.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.02.em,
    color = StarWarsColors.lightGray
)

private val default18 = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 18.sp,
    lineHeight = 24.sp,
    fontWeight = FontWeight.Normal,
    letterSpacing = 0.02.em,
)

private val default13 = TextStyle(
    fontFamily = FontFamily.Default,
    fontSize = 13.sp,
    lineHeight = 16.sp,
    fontWeight = FontWeight.Normal,
)
