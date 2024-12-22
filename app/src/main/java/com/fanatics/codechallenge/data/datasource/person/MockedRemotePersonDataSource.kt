package com.fanatics.codechallenge.data.datasource.person

import com.fanatics.codechallenge.data.database.FakePeopleAPI
import com.fanatics.codechallenge.data.model.Person
import com.fanatics.codechallenge.di.annotation.MockedData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Works with fake API, provides a person data.
 */
@MockedData
class MockedRemotePersonDataSource @Inject constructor(
    private val fakePeopleAPI: FakePeopleAPI,
) : RemotePersonDataSource {

    private val _dataFlow: MutableStateFlow<Person?> = MutableStateFlow(null)
    override val dataFlow: Flow<Person?> get() = _dataFlow

    override suspend fun request(id: Long) {
        _dataFlow.update {
            fakePeopleAPI.requestPerson(id)
        }
    }

    override fun clear() {
        _dataFlow.update { null }
    }
}
