package com.fanatics.codechallenge.ui.screen.home

sealed interface UIAction {
    data object ObservePeople : UIAction
    data object RefreshPeople : UIAction
    data class ChosePerson(val id: Long) : UIAction
}
