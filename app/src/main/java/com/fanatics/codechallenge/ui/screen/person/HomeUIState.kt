package com.fanatics.codechallenge.ui.screen.person

import androidx.compose.runtime.Stable
import com.fanatics.codechallenge.data.model.Person

@Stable
sealed interface PersonUIState {
    data object Loading : PersonUIState

    data class Error(
        val message: String,
    ) : PersonUIState

    data class Success(
        val person: Person,
    ) : PersonUIState
}
