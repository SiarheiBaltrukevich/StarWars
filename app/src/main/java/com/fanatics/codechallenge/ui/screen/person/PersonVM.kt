package com.fanatics.codechallenge.ui.screen.person

import android.content.res.Resources
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fanatics.codechallenge.R
import com.fanatics.codechallenge.data.model.Person
import com.fanatics.codechallenge.data.repository.PeopleRepository
import com.fanatics.codechallenge.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class PersonVM @Inject constructor(
    private val resources: Resources,
    private val peopleRepository: PeopleRepository,
    private val dispatcherProvider: DispatcherProvider,
) : ViewModel() {

    private val _uiState: MutableStateFlow<PersonUIState> = MutableStateFlow(PersonUIState.Loading)
    val uiState: Flow<PersonUIState> get() = _uiState.asStateFlow()

    fun handleUIAction(action: UIAction) = when (action) {
        UIAction.ObserveChosenPerson -> observeChosenPerson()
        UIAction.BackToHome -> resetChosenPerson()
    }

    private fun observeChosenPerson() {
        peopleRepository.chosenPersonFlow.onEach { person ->
            if (person == null) {
                showFailedUI(resources.getString(R.string.no_person_exception))
            } else {
                showSuccessUI(person)
            }
        }.launchIn(viewModelScope + dispatcherProvider.default)
    }

    private fun showSuccessUI(person: Person) {
        _uiState.update {
            PersonUIState.Success(person = person)
        }
    }

    private fun showFailedUI(msg: String) {
        _uiState.update { PersonUIState.Error(message = msg) }
    }

    private fun resetChosenPerson() {
        peopleRepository.chosePerson(null)
    }
}