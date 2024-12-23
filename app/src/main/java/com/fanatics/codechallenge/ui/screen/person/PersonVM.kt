package com.fanatics.codechallenge.ui.screen.person

import com.fanatics.codechallenge.data.model.Person
import com.fanatics.codechallenge.data.repository.PeopleRepository
import com.fanatics.codechallenge.ui.screen.BaseViewModel
import com.fanatics.codechallenge.util.DispatcherProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class PersonVM @Inject constructor(
    private val peopleRepository: PeopleRepository,
    private val dispatcherProvider: DispatcherProvider,
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<PersonUIState> = MutableStateFlow(PersonUIState.Loading)
    val uiState: Flow<PersonUIState> get() = _uiState.asStateFlow()

    fun handleUIAction(action: UIAction) = when (action) {
        UIAction.ObservePerson -> observeChosenPerson()
        UIAction.BackToHome -> resetChosenPerson()
    }

    private var observePersonJob: Job? = null
    private fun observeChosenPerson() {
        observePersonJob = peopleRepository.personFlow
            .catch { throwable ->
                showFailedUI(msg = throwable.localizedMessage.orEmpty())
            }.onEach { person ->
                if (person == null) showLoadingUI() else showSuccessUI(person)
            }.launchIn(safeViewModelScope + dispatcherProvider.default)
    }

    private fun showSuccessUI(person: Person) {
        _uiState.update { PersonUIState.Success(person = person) }
    }

    private fun showFailedUI(msg: String) {
        _uiState.update { PersonUIState.Error(message = msg) }
    }

    private fun showLoadingUI() {
        _uiState.update { PersonUIState.Loading }
    }

    private fun resetChosenPerson() {
        observePersonJob?.cancel()
        peopleRepository.clearPerson()
    }
}
