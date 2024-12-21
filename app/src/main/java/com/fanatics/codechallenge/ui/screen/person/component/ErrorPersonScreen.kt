package com.fanatics.codechallenge.ui.screen.person.component

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
import com.fanatics.codechallenge.ui.screen.person.PersonUIState
import com.fanatics.codechallenge.ui.screen.person.UIAction
import com.fanatics.codechallenge.ui.theme.SWTheme
import com.fanatics.codechallenge.ui.theme.StarWarsAppTheme

@Composable
fun BoxScope.ErrorPersonScreen(
    state: PersonUIState.Error,
    onAction: (UIAction) -> Unit
) {
    val onBackAction = remember { { onAction(UIAction.BackToHome) } }

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
            onClick = onBackAction
        ) {
            Text(
                text = stringResource(R.string.button_back_to_home)
            )
        }
    }
}

@Preview
@Composable
private fun ErrorPersonScreenPreview() {
    StarWarsAppTheme {
        Box(
            modifier = Modifier
                .background(SWTheme.colors.common.gradientBackground)
                .fillMaxSize()
        ) {
            ErrorPersonScreen(
                state = PersonUIState.Error(stringResource(R.string.no_person_exception))
            ) { }
        }
    }
}
