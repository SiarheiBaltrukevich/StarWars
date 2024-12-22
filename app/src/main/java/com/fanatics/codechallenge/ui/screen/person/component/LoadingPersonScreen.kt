package com.fanatics.codechallenge.ui.screen.person.component

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
fun LoadingPersonScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            modifier = modifier.align(Alignment.Center),
            style = SWTheme.typography.home.info,
            text = stringResource(R.string.person_loading),
        )
    }
}

@Preview
@Composable
private fun LoadingPersonScreenPreview() {
    StarWarsAppTheme {
        LoadingPersonScreen(
            modifier = Modifier.background(SWTheme.colors.common.gradientBackground)
        )
    }
}
