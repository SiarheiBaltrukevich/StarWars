package com.fanatics.codechallenge.data.repository

import android.util.Log
import com.fanatics.codechallenge.data.datasource.people.RemotePeopleDataSource
import com.fanatics.codechallenge.data.datasource.person.RemotePersonDataSource
import com.fanatics.codechallenge.data.model.Person
import com.fanatics.codechallenge.di.annotation.MockedData
import com.fanatics.codechallenge.util.DispatcherProvider
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRepository @Inject constructor(
    @MockedData private val remotePeopleDataSource: RemotePeopleDataSource,
    @MockedData private val remotePersonDataSource: RemotePersonDataSource,
    private val dispatcherProvider: DispatcherProvider,
    private val coroutineScope: CoroutineScope,
) {
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(this::class.simpleName, "Error related to the repository.", throwable)
    }

    val chosenPersonFlow: Flow<Person?>
        get() = remotePersonDataSource.dataFlow

    val peopleFlow: Flow<List<Person>>
        get() = remotePeopleDataSource.dataFlow.onEach { people ->
            if (people.isEmpty()) refreshPeople()
        }

    fun chosePerson(personID: Long) {
        coroutineScope.launch(dispatcherProvider.io + exceptionHandler) {
            remotePersonDataSource.request(personID)
        }
    }

    fun clearPerson() {
        remotePersonDataSource.clear()
    }

    fun refreshPeople() {
        coroutineScope.launch(dispatcherProvider.io + exceptionHandler) {
            remotePeopleDataSource.refresh()
        }
    }
}
