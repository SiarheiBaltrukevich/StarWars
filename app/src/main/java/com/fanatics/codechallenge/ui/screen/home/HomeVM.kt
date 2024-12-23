package com.fanatics.codechallenge.ui.screen.home

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
class HomeVM @Inject constructor(
    private val peopleRepository: PeopleRepository,
    private val dispatcherProvider: DispatcherProvider,
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState.Loading)
    val uiState: Flow<HomeUIState> get() = _uiState.asStateFlow()

    fun handleUIAction(action: UIAction) = when (action) {
        UIAction.ObservePeople -> observePeopleRepository()
        UIAction.RefreshPeople -> refreshPeople()
        is UIAction.ChosePerson -> chosePerson(action.id)
    }

    private var observePeopleJob: Job? = null
    private fun observePeopleRepository() {
        observePeopleJob?.cancel()
        observePeopleJob = peopleRepository.peopleFlow
            .catch { throwable ->
                showFailedUI(msg = throwable.localizedMessage.orEmpty())
            }.onEach { people ->
                if (people.isEmpty()) showLoadingUI() else showSuccessUI(people)
            }.launchIn(safeViewModelScope + dispatcherProvider.default)
    }

    private fun showSuccessUI(people: List<Person>) {
        _uiState.update { HomeUIState.Success(people) }
    }

    private fun showFailedUI(msg: String) {
        _uiState.update { HomeUIState.Error(message = msg) }
    }

    private fun showLoadingUI() {
        _uiState.update { HomeUIState.Loading }
    }

    private fun chosePerson(id: Long) {
        peopleRepository.chosePerson(id)
    }

    private fun refreshPeople() {
        showLoadingUI()
        observePeopleRepository()
        peopleRepository.refreshPeople()
    }
}
