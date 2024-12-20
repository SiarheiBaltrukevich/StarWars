package com.fanatics.codechallenge.data.datasource.people.remote

import android.app.Person
import kotlinx.coroutines.flow.Flow

interface RemotePeopleDataSource {

    val dataFlow: Flow<List<Person>>

    fun refresh()
}