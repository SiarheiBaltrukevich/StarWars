package com.fanatics.codechallenge.ui.screen.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fanatics.codechallenge.ui.screen.home.HomeUIState
import com.fanatics.codechallenge.ui.screen.home.UIAction
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
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = state.message
        )
        Button(
            modifier = Modifier,
            onClick = onRefreshAction
        ) {
            Text(
                text = "refresh"
            )
        }
    }
}

@Preview
@Composable
private fun ErrorHomeScreenPreview() {
    StarWarsAppTheme {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            ErrorHomeScreen(
                state = HomeUIState.Error("some message")
            ) { }
        }
    }
}