package com.fanatics.codechallenge.data.repository

import android.util.Log
import com.fanatics.codechallenge.data.datasource.chosenperson.LocalChosenPersonIdDataSource
import com.fanatics.codechallenge.data.datasource.people.remote.RemotePeopleDataSource
import com.fanatics.codechallenge.data.model.Person
import com.fanatics.codechallenge.di.annotation.MockData
import com.fanatics.codechallenge.util.DispatcherProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRepository @Inject constructor(
    @MockData private val remotePeopleDataSource: RemotePeopleDataSource,
    private val localChosenPersonIdDataSource: LocalChosenPersonIdDataSource,
    private val dispatcherProvider: DispatcherProvider,
    private val coroutineScope: CoroutineScope,
) {
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(this::class.simpleName, "Error related to the repository.", throwable)
    }

    private val _chosenPersonFlow: MutableStateFlow<Person?> = MutableStateFlow(null)
    val chosenPersonFlow: Flow<Person?> get() = _chosenPersonFlow.asStateFlow()

    private val _peopleFLow: MutableStateFlow<List<Person>> = MutableStateFlow(emptyList())
    val peopleFlow: Flow<List<Person>>
        get() = _peopleFLow.onEach { people ->
            if (people.isEmpty()) refreshPeople()
        }

    fun chosePerson(personID: Long?) {
        coroutineScope.launch(dispatcherProvider.io + exceptionHandler) {
            localChosenPersonIdDataSource.update(personID)
            _chosenPersonFlow.update {
                _peopleFLow.value.firstOrNull { it.id == personID }
            }
        }
    }

    fun clear() {
        coroutineScope.launch(dispatcherProvider.io + exceptionHandler) {
            _peopleFLow.value = emptyList()
            localChosenPersonIdDataSource.clear()
        }
    }

    fun refreshPeople() {
       remotePeopleDataSource.refresh()
    }
}
