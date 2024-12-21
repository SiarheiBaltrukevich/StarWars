package com.fanatics.codechallenge.data.datasource.people

import com.fanatics.codechallenge.data.model.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Keeps people data locally to load them as fast as it possible on the app's start up.
 */
internal class LocalPeopleDataSource @Inject constructor() {

    private val _dataFlow: MutableStateFlow<List<Person>> = MutableStateFlow(emptyList())
    val dataFlow: Flow<List<Person>> get() = _dataFlow

    fun update(data: List<Person>) {
        // write in DB or file.
        _dataFlow.update { data }
    }
}
