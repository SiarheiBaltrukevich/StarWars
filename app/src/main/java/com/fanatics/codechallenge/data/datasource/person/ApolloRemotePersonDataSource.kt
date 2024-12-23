package com.fanatics.codechallenge.data.datasource.person

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.fanatics.codeChanllenge.PersonQuery
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
        val person = apolloClient
            .query(PersonQuery(id.toString())).await()
            .data?.character()
            ?.toPerson()

        _dataFlow.update { person }
    }

    override fun clear() {
        _dataFlow.update { null }
    }

    private fun PersonQuery.Character.toPerson(): Person = Person(
        id = id()?.toLong() ?: 0L,
        name = name().orEmpty(),
        gender = gender().orEmpty(),
        status = status().orEmpty(),
        species = species().orEmpty(),
    )
}
