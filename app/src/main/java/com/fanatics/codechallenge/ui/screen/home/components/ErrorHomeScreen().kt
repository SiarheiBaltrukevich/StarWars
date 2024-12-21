package com.fanatics.codechallenge.ui.screen.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.fanatics.codechallenge.R
import com.fanatics.codechallenge.ui.screen.home.HomeUIState
import com.fanatics.codechallenge.ui.screen.home.UIAction
import com.fanatics.codechallenge.ui.theme.SWTheme
import com.fanatics.codechallenge.ui.theme.StarWarsAppTheme

@Composable
fun BoxScope.ErrorHomeScreen(
    state: HomeUIState.Error,
    onAction: (UIAction) -> Unit
) {
    val onRefreshAction = remember { { onAction(UIAction.RefreshPeople) } }

    Column(
        modifier = Modifier.align(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(SWTheme.dimens.padding.p16)
    ) {
        Text(
            textAlign = TextAlign.Center,
            style = SWTheme.typography.home.info,
            text = state.message
        )
        Button(
            modifier = Modifier,
            onClick = onRefreshAction
        ) {
            Text(
                text = stringResource(R.string.button_refresh)
            )
        }
    }
}

@Preview
@Composable
private fun ErrorHomeScreenPreview() {
    StarWarsAppTheme {
        Box(
            modifier = Modifier
                .background(SWTheme.colors.home.gradientBackground)
                .fillMaxSize()
        ) {
            ErrorHomeScreen(
                state = HomeUIState.Error(stringResource(R.string.no_people_exception))
            ) { }
        }
    }
}
