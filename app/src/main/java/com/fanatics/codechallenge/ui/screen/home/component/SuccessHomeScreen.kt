package com.fanatics.codechallenge.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fanatics.codechallenge.data.model.Person
import com.fanatics.codechallenge.ui.screen.home.HomeUIState
import com.fanatics.codechallenge.ui.screen.home.UIAction
import com.fanatics.codechallenge.ui.theme.SWTheme
import com.fanatics.codechallenge.ui.theme.StarWarsAppTheme

@Composable
fun SuccessHomeScreen(
    state: HomeUIState.Success,
    onAction: (UIAction) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val onPersonClick = remember { { id: Long -> onAction(UIAction.ChosePerson(id)) } }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = lazyListState,
        horizontalAlignment = Alignment.Start,
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
                                TODO("navigate to person details")
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
                }
            }
        }
    }
}

@Preview
@Composable
private fun SuccessHomeScreenPreview() {
    StarWarsAppTheme {
        Box(
            modifier = Modifier
                .background(SWTheme.colors.common.gradientBackground)
                .fillMaxSize()
        ) {
            SuccessHomeScreen(
                state = HomeUIState.Success(
                    people = listOf(
                        Person(
                            id = 4,
                            name = "Niall",
                            height = 1.75,
                            mass = 76.5,
                            homeworld = "homeworld 1"
                        ),
                        Person(
                            id = 3,
                            name = "Jack",
                            height = 1.65,
                            mass = 76.5,
                            homeworld = "homeworld 2"
                        ),
                        Person(
                            id = 2,
                            name = "Lucia",
                            height = 1.72,
                            mass = 66.5,
                            homeworld = "homeworld 3"
                        ),
                        Person(
                            id = 1,
                            name = "Paul",
                            height = 1.85,
                            mass = 86.5,
                            homeworld = "homeworld 4"
                        ),
                    )
                )
            ) { }
        }
    }
}