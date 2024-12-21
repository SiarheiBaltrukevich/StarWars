package com.fanatics.codechallenge.ui.screen.person

sealed interface UIAction {
    data object ObserveChosenPerson : UIAction
    data object BackToHome : UIAction
}
