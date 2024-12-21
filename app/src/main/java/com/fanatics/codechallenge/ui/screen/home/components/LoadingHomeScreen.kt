package com.fanatics.codechallenge.ui.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fanatics.codechallenge.R
import com.fanatics.codechallenge.ui.theme.SWTheme
import com.fanatics.codechallenge.ui.theme.StarWarsAppTheme

@Composable
fun BoxScope.LoadingHomeScreen(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.align(Alignment.Center),
        style = SWTheme.typography.home.info,
        text = stringResource(R.string.home_loading),
    )
}

@Preview
@Composable
private fun LoadingHomeScreenPreview() {
    StarWarsAppTheme {
        Box(
            modifier = Modifier
                .background(SWTheme.colors.home.gradientBackground)
                .fillMaxSize()
        ) {
            LoadingHomeScreen()
        }
    }
}
