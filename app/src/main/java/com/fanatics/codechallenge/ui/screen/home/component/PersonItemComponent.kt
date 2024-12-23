package com.fanatics.codechallenge.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fanatics.codechallenge.R
import com.fanatics.codechallenge.data.model.Person
import com.fanatics.codechallenge.ui.theme.SWTheme
import com.fanatics.codechallenge.ui.theme.StarWarsAppTheme

@Composable
internal fun PersonItemComponent(
    person: Person,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(SWTheme.colors.home.personItem.background)
            .padding(
                vertical = SWTheme.dimens.padding.p16,
                horizontal = SWTheme.dimens.padding.p24,
            ),
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            InfoText(text = person.name)
            InfoText(text = stringResource(R.string.person_item_height, person.gender.toString()))
            InfoText(text = stringResource(R.string.person_item_mass, person.status.toString()))
        }
        Icon(
            modifier = Modifier.size(SWTheme.dimens.icon.small),
            painter = painterResource(id = R.drawable.ic_right_arrow),
            contentDescription = "open person info icon",
            tint = SWTheme.colors.home.personItem.iconColor,
        )
    }
}

@Composable
private fun InfoText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier,
        text = text,
        style = SWTheme.typography.home.person.info
    )
}

@Preview
@Composable
private fun PersonItemComponentPreview() {
    StarWarsAppTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(SWTheme.colors.common.gradientBackground)
        ) {
            PersonItemComponent(
                Person(
                    id = 4,
                    name = "Niall Smyth",
                    gender = "Male",
                    status = "Alive",
                    species = "Human"
                ),
            )
        }
    }
}