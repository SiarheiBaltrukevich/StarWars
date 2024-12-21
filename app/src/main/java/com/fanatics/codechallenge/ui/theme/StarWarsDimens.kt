package com.fanatics.codechallenge.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp

@Immutable
internal data class StarWarsDimens(
    val home: HomeDimens = HomeDimens(),
    val person: PersonDimens = PersonDimens(),
    val padding: Paddings = Paddings(),
    val icon: IconSize = IconSize(),
    val divider: DividerSize = DividerSize(),
)

@Immutable
internal data class HomeDimens(
    val header: HeaderDimens = HeaderDimens(height = 96.dp),
)

@Immutable
internal data class PersonDimens(
    val header: HeaderDimens = HeaderDimens(height = 72.dp),
    val dialogFraction: Float = 0.4f,
    val dialogShape: Shape = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
    ),
)

@Immutable
internal data class HeaderDimens(
    val height: Dp,
)

@Immutable
internal data class Paddings(
    val p2: Dp = 2.dp,
    val p4: Dp = 4.dp,
    val p8: Dp = 8.dp,
    val p12: Dp = 12.dp,
    val p16: Dp = 16.dp,
    val p24: Dp = 24.dp,
)

@Immutable
internal data class IconSize(
    val small: DpSize = DpSize(16.dp, 16.dp)
)

@Immutable
internal data class DividerSize(
    val small: Dp = 1.dp,
    val medium: Dp = 2.dp,
)
