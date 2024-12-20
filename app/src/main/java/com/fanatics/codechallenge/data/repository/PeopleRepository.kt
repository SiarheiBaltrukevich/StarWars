package com.fanatics.codechallenge.data.repository

import com.fanatics.codechallenge.data.datasource.people.LocalPeopleDataSource
import com.fanatics.codechallenge.data.datasource.people.MemoryPeopleDataSource
import com.fanatics.codechallenge.data.datasource.people.RemotePeopleDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class PeopleRepository @Inject constructor(
    private val remotePeopleDataSource: RemotePeopleDataSource,
    private val localPeopleDataSource: LocalPeopleDataSource,
    private val memoryPeopleDataSource: MemoryPeopleDataSource,
) {

}