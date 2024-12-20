package com.fanatics.codechallenge.data.datasource.people.local

import com.fanatics.codechallenge.data.model.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Keeps people collection during the session to have a fast access to data.
 */
internal class MemoryPeopleDataSource @Inject constructor() {

    private val _peopleFlow: MutableStateFlow<List<Person>> = MutableStateFlow(emptyList())
    val peopleFlow: Flow<List<Person>> get() = _peopleFlow

    fun update(data: List<Person>) {
        _peopleFlow.update { data }
    }

    fun clear() {
        _peopleFlow.update { emptyList() }
    }
}
