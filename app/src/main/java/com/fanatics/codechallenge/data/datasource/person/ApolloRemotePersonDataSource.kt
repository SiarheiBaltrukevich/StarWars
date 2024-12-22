package com.fanatics.codechallenge.data.datasource.person

import com.apollographql.apollo.ApolloClient
import com.fanatics.codechallenge.data.model.Person
import com.fanatics.codechallenge.di.annotation.Apollo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Works with graphQL API, provides a person data.
 */
@Apollo
class ApolloRemotePersonDataSource @Inject constructor(
    private val apolloClient: ApolloClient
) : RemotePersonDataSource {
    private val _dataFlow: MutableStateFlow<Person?> = MutableStateFlow(null)
    override val dataFlow: Flow<Person?> get() = _dataFlow

    override suspend fun request(id: Long) {
        _dataFlow.update {
            it // the schema is not working now. Do nothing.
        }
    }

    override fun clear() {
        _dataFlow.update { null }
    }
}
