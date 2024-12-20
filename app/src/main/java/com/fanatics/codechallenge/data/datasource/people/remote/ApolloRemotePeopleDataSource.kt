package com.fanatics.codechallenge.data.datasource.people.remote

import android.app.Person
import com.apollographql.apollo.ApolloClient
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Resource to work with Apollo schema
 */
internal class ApolloRemotePeopleDataSource @Inject constructor(
    private val apolloClient: ApolloClient
) : RemotePeopleDataSource {
    private val _dataFlow: MutableStateFlow<List<Person>> =
        MutableStateFlow(emptyList())
    override val dataFlow: Flow<List<Person>> get() = _dataFlow.asStateFlow()

    override fun refresh() {
        _dataFlow.update {
            it // schema is not working now.
            //apolloClient.query<List<Person>>().execute().data.orEmpty()
        }
    }

    // the mapper should be added here to convert response to domain model.
}
