package com.fanatics.codechallenge.ui.screen.person

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.fanatics.codechallenge.R
import com.fanatics.codechallenge.ui.screen.person.component.ErrorPersonScreen
import com.fanatics.codechallenge.ui.screen.person.component.LoadingPersonScreen
import com.fanatics.codechallenge.ui.screen.person.component.SuccessPersonScreen
import com.fanatics.codechallenge.ui.theme.SWTheme
import com.fanatics.codechallenge.ui.theme.StarWarsAppTheme

@Composable
fun PersonScreen() {
    val viewModel: PersonVM = hiltViewModel()
    val state: PersonUIState by viewModel.uiState.collectAsState(PersonUIState.Loading)

    LaunchedEffect(key1 = Unit) {
        viewModel.handleUIAction(UIAction.ObserveChosenPerson)
    }

    ShowPersonUI(state, viewModel::handleUIAction)
}

@Composable
private fun ShowPersonUI(
    state: PersonUIState,
    onAction: (UIAction) -> Unit
) = BasePersonComponent {
    when(state) {
        PersonUIState.Loading -> LoadingPersonScreen()
        is PersonUIState.Error -> ErrorPersonScreen(state, onAction)
        is PersonUIState.Success -> SuccessPersonScreen(state)
    }
}

@Composable
private fun BasePersonComponent(
    content: @Composable BoxScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SWTheme.colors.common.gradientBackground)
    ) {
        Header(modifier = Modifier.systemBarsPadding())

        Box(modifier = Modifier.fillMaxSize()) {
            content()
        }
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .height(SWTheme.dimens.person.header.height)
            .fillMaxWidth()
            .padding(horizontal = SWTheme.dimens.padding.p16)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = { TODO("back to home action") }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(SWTheme.dimens.padding.p16)
    ) {
        Icon(
            modifier = Modifier.size(SWTheme.dimens.icon.small),
            painter = painterResource(id = R.drawable.ic_left_arrow),
            contentDescription = "back to home icon",
            tint = SWTheme.colors.home.personItem.iconColor,
        )

        Text(
            text = stringResource(R.string.home_header),
            style = SWTheme.typography.person.header.main
        )
    }
}

@Preview
@Composable
private fun BaseHomeComponentPreview() {
    StarWarsAppTheme {
        BasePersonComponent {
            Box(modifier = Modifier.fillMaxSize())
        }
    }
}
