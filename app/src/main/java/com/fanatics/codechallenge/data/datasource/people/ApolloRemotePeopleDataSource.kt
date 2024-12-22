package com.fanatics.codechallenge.data.datasource.people

import com.apollographql.apollo.ApolloClient
import com.fanatics.codechallenge.data.model.Person
import com.fanatics.codechallenge.di.annotation.Apollo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Works with graphQL API, provides a people data.
 */
@Apollo
internal class ApolloRemotePeopleDataSource @Inject constructor(
    private val apolloClient: ApolloClient
) : RemotePeopleDataSource {
    private val _dataFlow: MutableStateFlow<List<Person>> = MutableStateFlow(emptyList())
    override val dataFlow: Flow<List<Person>> get() = _dataFlow.asStateFlow()

    override suspend fun refresh() {
        _dataFlow.update {
            it // the schema is not working now. Do nothing.
        }
    }
}
