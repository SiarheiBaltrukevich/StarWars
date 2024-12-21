package com.fanatics.codechallenge.ui.screen.home

import com.fanatics.codechallenge.data.model.Person

sealed interface HomeUIState {
    data object Loading : HomeUIState
    data class Error(
        val message: String,
    ) : HomeUIState
    data class Success(
        val people: List<Person>
    ) : HomeUIState
}
