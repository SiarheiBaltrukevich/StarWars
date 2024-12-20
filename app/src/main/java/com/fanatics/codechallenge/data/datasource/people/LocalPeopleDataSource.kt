package com.fanatics.codechallenge.data.datasource.people

import com.fanatics.codechallenge.data.model.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

internal class LocalPeopleDataSource @Inject constructor() {

    private val _peopleFlow: MutableStateFlow<List<Person>> = MutableStateFlow(emptyList())
    val peopleFlow: Flow<List<Person>> get() = _peopleFlow
}
