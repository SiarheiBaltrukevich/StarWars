package com.fanatics.codechallenge.data.datasource.people

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.fanatics.codeChanllenge.PeopleQuery
import com.fanatics.codeChanllenge.PersonQuery
import com.fanatics.codechallenge.data.model.Person
import com.fanatics.codechallenge.di.annotation.Apollo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
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
    override val dataFlow: Flow<List<Person>>
        get() = _dataFlow.onEach { if (it.isEmpty()) refresh() }


    override suspend fun refresh() {
        val people = apolloClient
            .query(PeopleQuery()).await()
            .data?.characters()
            ?.results().orEmpty()

        _dataFlow.update { people.map { it.toPerson() } }
    }

    private fun PeopleQuery.Result.toPerson(): Person = Person(
        id = id()?.toLong() ?: 0L,
        name = name().orEmpty(),
        gender = gender().orEmpty(),
        status = status().orEmpty(),
        species = species().orEmpty(),
    )
}
