package com.fanatics.codechallenge.data.datasource.people.remote

import com.fanatics.codechallenge.data.model.Person
import com.fanatics.codechallenge.di.annotation.MockedData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * An alternative source of remote data source, since the main is not working.
 */
@MockedData
internal class MockRemotePeopleDataSource @Inject constructor() : RemotePeopleDataSource {

    private val _dataFlow: MutableStateFlow<List<Person>> = MutableStateFlow(generatePeople())
    override val dataFlow: Flow<List<Person>> get() = _dataFlow.asStateFlow()

    override fun refresh() {
        _dataFlow.update { generatePeople() }
    }

    private fun generatePeople(): List<Person> {
        return listOf(
            Person(
                id = 4,
                name = "Niall",
                height = 1.75,
                mass = 76.5,
                homeworld = "homeworld 1"
            ),
            Person(
                id = 3,
                name = "Jack",
                height = 1.65,
                mass = 76.5,
                homeworld = "homeworld 2"
            ),
            Person(
                id = 2,
                name = "Lucia",
                height = 1.72,
                mass = 66.5,
                homeworld = "homeworld 3"
            ),
            Person(
                id = 1,
                name = "Paul",
                height = 1.85,
                mass = 86.5,
                homeworld = "homeworld 4"
            ),
        )
    }
}
