package com.fanatics.codechallenge.ui.screen.person

sealed interface UIAction {
    data object ObservePerson : UIAction
    data object BackToHome : UIAction
}
