package com.fanatics.codechallenge.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.fanatics.codechallenge.R
import com.fanatics.codechallenge.ui.screen.home.component.ErrorHomeScreen
import com.fanatics.codechallenge.ui.screen.home.component.LoadingHomeScreen
import com.fanatics.codechallenge.ui.screen.home.component.SuccessHomeScreen
import com.fanatics.codechallenge.ui.theme.SWTheme
import com.fanatics.codechallenge.ui.theme.StarWarsAppTheme

@Composable
fun HomeScreen(
    navController: NavController
) {
    val viewModel: HomeVM = hiltViewModel()
    val state: HomeUIState by viewModel.uiState.collectAsState(HomeUIState.Loading)

    LaunchedEffect(key1 = Unit) {
        viewModel.handleUIAction(UIAction.ObservePeople)
    }

    BaseHomeComponent(
        state = state,
        navController = navController,
        onAction = viewModel::handleUIAction
    )
}

@Composable
private fun BaseHomeComponent(
    state: HomeUIState,
    navController: NavController,
    onAction: (UIAction) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SWTheme.colors.common.gradientBackground)
            .navigationBarsPadding()
    ) {
        Header(modifier = Modifier.statusBarsPadding())

        when(state) {
            HomeUIState.Loading -> LoadingHomeScreen()
            is HomeUIState.Error -> ErrorHomeScreen(
                state = state,
                onAction = onAction
            )
            is HomeUIState.Success -> SuccessHomeScreen(
                state = state,
                navController = navController,
                onAction = onAction
            )
        }
    }
}

@Composable
private fun Header(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(SWTheme.dimens.home.header.height)
        ) {
            Text(
                modifier = Modifier
                    .padding(horizontal = SWTheme.dimens.padding.p24)
                    .align(Alignment.CenterStart),
                text = stringResource(R.string.home_header),
                style = SWTheme.typography.home.header.main
            )
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = SWTheme.dimens.divider.small,
            color = SWTheme.colors.home.header.divider
        )
    }
}

@Preview
@Composable
private fun BaseHomeComponentPreview() {
    StarWarsAppTheme {
        BaseHomeComponent(
            state = HomeUIState.Loading,
            navController = NavController(LocalContext.current),
            onAction = {}
        )
    }
}
