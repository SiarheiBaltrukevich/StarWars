package com.fanatics.codechallenge.data.datasource.people.remote

import com.fanatics.codechallenge.data.model.Person
import kotlinx.coroutines.flow.Flow

/**
 * Provides the remote people data.
 */
interface RemotePeopleDataSource {

    val dataFlow: Flow<List<Person>>

    fun refresh()
}
