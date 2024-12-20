package com.fanatics.codechallenge.data.datasource.people.remote

import android.app.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * An alternative source of remote data source, since the main is not working.
 */
internal class MockRemoteDataSource @Inject constructor() : RemotePeopleDataSource {

    private val _dataFlow: MutableStateFlow<List<Person>> = MutableStateFlow(emptyList())
    override val dataFlow: Flow<List<Person>> get() = _dataFlow.asStateFlow()

    override fun refresh() {
        _dataFlow.update { generatePeople() }
    }

    private fun generatePeople(): List<Person> {
        return emptyList()
    }
}