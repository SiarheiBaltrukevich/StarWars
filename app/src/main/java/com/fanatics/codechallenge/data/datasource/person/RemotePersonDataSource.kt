package com.fanatics.codechallenge.data.datasource.person

import com.fanatics.codechallenge.data.model.Person
import kotlinx.coroutines.flow.Flow

interface RemotePersonDataSource {

    val dataFlow: Flow<Person?>

    suspend fun request(id: Long)

    fun clear()
}
