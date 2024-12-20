package com.fanatics.codechallenge.data.datasource.people.local

import com.fanatics.codechallenge.data.model.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class LocalPeopleDataSource @Inject constructor() {

    private val _peopleFlow: MutableStateFlow<List<Person>> = MutableStateFlow(emptyList())
    val peopleFlow: Flow<List<Person>> get() = _peopleFlow

    fun update(data: List<Person>) {
        // write in DB or file.
        _peopleFlow.update { data }
    }
}
