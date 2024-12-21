package com.fanatics.codechallenge.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
internal data class StarWarsDimens(
    val home: HomeDimens = HomeDimens()
)

@Immutable
internal data class HomeDimens(
    val headerHeight: Dp = 96.dp
)