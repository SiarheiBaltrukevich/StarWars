package com.fanatics.codechallenge.ui.screen.home.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.tooling.preview.Preview
import com.fanatics.codechallenge.ui.theme.StarWarsAppTheme

@Composable
fun BoxScope.LoadingHomeScreen(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.align(Alignment.Center),
        text = "Loading...",
    )
}

@Preview
@Composable
private fun LoadingHomeScreenPreview() {
    StarWarsAppTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            LoadingHomeScreen()
        }
    }
}
