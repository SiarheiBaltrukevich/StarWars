package com.fanatics.codechallenge.ui.screen.person.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fanatics.codechallenge.R
import com.fanatics.codechallenge.data.model.Person
import com.fanatics.codechallenge.ui.screen.person.PersonUIState
import com.fanatics.codechallenge.ui.theme.SWTheme
import com.fanatics.codechallenge.ui.theme.StarWarsAppTheme

@Composable
fun SuccessPersonScreen(
    state: PersonUIState.Success,
) {
    var isDetailsVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .padding(SWTheme.dimens.padding.p24)
                .align(Alignment.TopStart)
                .clickable {
                    isDetailsVisible = !isDetailsVisible
                },
            style = SWTheme.typography.person.info,
            text = stringResource(R.string.person_text_with_link, state.person.name),
        )

        if (isDetailsVisible) DetailsDialog(
            modifier = Modifier
                .fillMaxHeight(SWTheme.dimens.person.dialogFraction)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            person = state.person,
        )
    }
}

@Composable
private fun DetailsDialog(
    person: Person,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(
                shape = SWTheme.dimens.person.dialogShape,
                color = SWTheme.colors.person.dialogBackground,
            )
            .padding(SWTheme.dimens.padding.p24)
    ) {
        Text(
            style = SWTheme.typography.person.dialog.header,
            text = person.name,
        )
        Text(
            style = SWTheme.typography.person.dialog.info,
            text = stringResource(R.string.person_homeworld, person.homeworld),
        )
    }
}

@Preview
@Composable
private fun SuccessPersonScreenPreview() {
    StarWarsAppTheme {
        Box(
            modifier = Modifier
                .background(SWTheme.colors.common.gradientBackground)
                .fillMaxSize()
        ) {
            SuccessPersonScreen(
                state = PersonUIState.Success(
                    person = Person(
                        id = 4,
                        name = "Niall Fastfood",
                        height = 1.75,
                        mass = 76.5,
                        homeworld = "burger bar"
                    ),
                )
            )
        }
    }
}
