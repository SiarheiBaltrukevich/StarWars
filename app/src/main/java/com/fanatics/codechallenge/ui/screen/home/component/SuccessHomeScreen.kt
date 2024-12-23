package com.fanatics.codechallenge.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.fanatics.codechallenge.data.model.Person
import com.fanatics.codechallenge.ui.navigation.AppScreens
import com.fanatics.codechallenge.ui.screen.home.HomeUIState
import com.fanatics.codechallenge.ui.screen.home.UIAction
import com.fanatics.codechallenge.ui.theme.SWTheme
import com.fanatics.codechallenge.ui.theme.StarWarsAppTheme

@Composable
fun SuccessHomeScreen(
    state: HomeUIState.Success,
    navController: NavController,
    onAction: (UIAction) -> Unit,
    modifier: Modifier = Modifier,
) {
    val lazyListState = rememberLazyListState()
    val onPersonClick = remember { { id: Long -> onAction(UIAction.ChosePerson(id)) } }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        state = lazyListState,
    ) {
        itemsIndexed(
            items = state.people,
            key = { _, person -> person.id },
        ) { index, person ->
            Column {
                PersonItemComponent(
                    modifier = Modifier
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = {
                                onPersonClick(person.id)
                                navController.navigate(route = AppScreens.Person.name)
                            }
                        ),
                    person = person
                )
                if (state.people.lastIndex != index) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(SWTheme.dimens.divider.small)
                            .background(SWTheme.colors.home.gradientDivider),
                    )
                } else {
                    Spacer(
                        modifier = Modifier.navigationBarsPadding(),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SuccessHomeScreenPreview() {
    StarWarsAppTheme {
        SuccessHomeScreen(
            modifier = Modifier.background(SWTheme.colors.common.gradientBackground),
            state = HomeUIState.Success(
                people = listOf(
                    Person(
                        id = 4,
                        name = "Niall",
                        gender = "Male",
                        status = "Alive",
                        species = "Human"
                    ),
                    Person(
                        id = 3,
                        name = "Jack",
                        gender = "Male",
                        status = "Alive",
                        species = "Human"
                    ),
                )
            ),
            navController = NavController(LocalContext.current),
            onAction = {}
        )
    }
}