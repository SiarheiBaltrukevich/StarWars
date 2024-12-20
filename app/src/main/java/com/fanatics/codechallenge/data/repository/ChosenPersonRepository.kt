package com.fanatics.codechallenge.data.repository

import com.fanatics.codechallenge.data.datasource.chosenperson.LocalChosenPersonIdDataSource
import com.fanatics.codechallenge.data.datasource.chosenperson.MemoryChosenPersonDataSource
import javax.inject.Inject

internal class ChosenPersonRepository @Inject constructor(
    private val localChosenPersonIdDataSource: LocalChosenPersonIdDataSource,
    private val memoryChosenPersonDataSource: MemoryChosenPersonDataSource,
) {

}