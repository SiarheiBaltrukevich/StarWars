package com.fanatics.codechallenge.ui.screen.home

import androidx.compose.runtime.Stable
import com.fanatics.codechallenge.data.model.Person

@Stable
sealed interface HomeUIState {
    data object Loading : HomeUIState

    data class Error(
        val message: String,
    ) : HomeUIState

    data class Success(
        val people: List<Person>
    ) : HomeUIState
}
