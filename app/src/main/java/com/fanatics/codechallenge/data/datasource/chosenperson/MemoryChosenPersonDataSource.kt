package com.fanatics.codechallenge.data.datasource.chosenperson

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Keeps chosen person id during the session to have a fast access to it.
 */
internal class MemoryChosenPersonDataSource @Inject constructor() {

    private val _personFlow: MutableStateFlow<Long?> = MutableStateFlow(null)
    val personFlow: Flow<Long?> get() = _personFlow

    fun update(id: Long?) {
        _personFlow.update { id }
    }

    fun clear() {
        _personFlow.update { null }
    }
}