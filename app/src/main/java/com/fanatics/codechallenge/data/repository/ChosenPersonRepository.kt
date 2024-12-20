package com.fanatics.codechallenge.data.repository

import com.fanatics.codechallenge.data.datasource.chosenperson.LocalChosenPersonDataSource
import com.fanatics.codechallenge.data.datasource.chosenperson.MemoryChosenPersonDataSource
import javax.inject.Inject

internal class ChosenPersonRepository @Inject constructor(
    private val localChosenPersonDataSource: LocalChosenPersonDataSource,
    private val memoryChosenPersonDataSource: MemoryChosenPersonDataSource,
) {

}