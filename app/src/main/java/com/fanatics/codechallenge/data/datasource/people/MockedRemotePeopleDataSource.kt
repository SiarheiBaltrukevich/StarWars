package com.fanatics.codechallenge.data.datasource.people

import com.fanatics.codechallenge.data.database.FakePeopleAPI
import com.fanatics.codechallenge.data.model.Person
import com.fanatics.codechallenge.di.annotation.MockedData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Works with fake API, provides a people data.
 */
@MockedData
internal class MockedRemotePeopleDataSource @Inject constructor(
    private val fakePeopleAPI: FakePeopleAPI
) : RemotePeopleDataSource {

    private val _dataFlow: MutableStateFlow<List<Person>> = MutableStateFlow(emptyList())
    override val dataFlow: Flow<List<Person>> get() = _dataFlow.asStateFlow()

    override suspend fun refresh() {
        _dataFlow.update {
            fakePeopleAPI.requestPeople()
        }
    }
}
