package com.fanatics.codechallenge.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.fanatics.codechallenge.R
import com.fanatics.codechallenge.ui.screen.home.components.ErrorHomeScreen
import com.fanatics.codechallenge.ui.screen.home.components.LoadingHomeScreen
import com.fanatics.codechallenge.ui.screen.home.components.SuccessHomeScreen
import com.fanatics.codechallenge.ui.theme.SWTheme
import com.fanatics.codechallenge.ui.theme.StarWarsAppTheme

@Composable
fun HomeScreen() {
    val viewModel: HomeVM = hiltViewModel()
    val state: HomeUIState by viewModel.uiState.collectAsState(HomeUIState.Loading)

    LaunchedEffect(key1 = Unit) {
        viewModel.handleUIAction(UIAction.ObservePeople)
    }

    ShowHomeUI(state, viewModel::handleUIAction)
}

@Composable
private fun ShowHomeUI(
    state: HomeUIState,
    onAction: (UIAction) -> Unit
) = BaseHomeComponent {
    when(state) {
        HomeUIState.Loading -> LoadingHomeScreen()
        is HomeUIState.Error -> ErrorHomeScreen(state, onAction)
        is HomeUIState.Success -> SuccessHomeScreen(state, onAction)
    }
}

@Composable
private fun BaseHomeComponent(
    content: @Composable BoxScope.() -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Header()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(SWTheme.colors.home.gradientBackground)
        ) {
            content()
        }
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(SWTheme.colors.home.header.background)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(SWTheme.dimens.home.headerHeight)
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
                    .align(Alignment.CenterStart),
                text = stringResource(R.string.home_header),
                style = SWTheme.typography.home.header.main
            )
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = SWTheme.colors.home.header.divider
        )
    }
}

@Preview
@Composable
private fun BaseHomeComponentPreview() {
    StarWarsAppTheme {
        BaseHomeComponent {
            Box(modifier = Modifier.fillMaxSize())
        }
    }
}
