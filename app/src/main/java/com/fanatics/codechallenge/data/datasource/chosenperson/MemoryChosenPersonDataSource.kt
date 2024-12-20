package com.fanatics.codechallenge.data.datasource.chosenperson

import com.fanatics.codechallenge.data.model.Person
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

internal class MemoryChosenPersonDataSource @Inject constructor() {

    private val _personFlow: MutableStateFlow<Person?> = MutableStateFlow(null)
    val personFlow: Flow<Person?> get() = _personFlow

}