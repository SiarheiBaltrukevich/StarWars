package com.fanatics.codechallenge.data.repository

import com.fanatics.codechallenge.data.datasource.people.local.LocalPeopleDataSource
import com.fanatics.codechallenge.data.datasource.people.local.MemoryPeopleDataSource
import com.fanatics.codechallenge.data.datasource.people.remote.ApolloRemotePeopleDataSource
import com.fanatics.codechallenge.data.model.Person
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class PeopleRepository @Inject constructor(
    private val remotePeopleDataSource: ApolloRemotePeopleDataSource,
    private val localPeopleDataSource: LocalPeopleDataSource,
    private val memoryPeopleDataSource: MemoryPeopleDataSource,
) {

    val peopleFlow: Flow<List<Person>> =
        memoryPeopleDataSource.peopleFlow

}