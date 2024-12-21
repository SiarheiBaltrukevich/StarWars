package com.fanatics.codechallenge.ui.screen.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fanatics.codechallenge.data.model.Person
import com.fanatics.codechallenge.ui.screen.home.HomeUIState
import com.fanatics.codechallenge.ui.screen.home.UIAction
import com.fanatics.codechallenge.ui.theme.StarWarsAppTheme

@Composable
fun BoxScope.SuccessHomeScreen(
    state: HomeUIState.Success,
    onAction: (UIAction) -> Unit
) {
    val lazyListState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = lazyListState,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(
            items = state.people
        ) { person ->
            Text(
                modifier = Modifier.clickable {
                    onAction(UIAction.ChosePerson(person.id))
                },
                text = person.name
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
            SuccessHomeScreen(
                state = HomeUIState.Success(
                    people = listOf(
                        Person(
                            id = 4,
                            name = "Niall",
                            height = 1.75,
                            mass = 76.5,
                            description = "some clever person"
                        ),
                        Person(
                            id = 3,
                            name = "Jack",
                            height = 1.65,
                            mass = 76.5,
                            description = "some interesting person"
                        ),
                        Person(
                            id = 2,
                            name = "Lucia",
                            height = 1.72,
                            mass = 66.5,
                            description = "some beauty person"
                        ),
                        Person(
                            id = 1,
                            name = "Paul",
                            height = 1.85,
                            mass = 86.5,
                            description = "some tall person"
                        ),
                    )
                )
            ) { }
        }
    }
}