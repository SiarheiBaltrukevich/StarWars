package com.fanatics.codechallenge.di

import com.fanatics.codechallenge.data.datasource.people.remote.ApolloRemotePeopleDataSource
import com.fanatics.codechallenge.data.datasource.people.remote.MockRemotePeopleDataSource
import com.fanatics.codechallenge.data.datasource.people.remote.RemotePeopleDataSource
import com.fanatics.codechallenge.di.annotation.Apollo
import com.fanatics.codechallenge.di.annotation.MockedData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DIBindersModule {

    @Binds
    @Apollo
    internal abstract fun bindApolloRemotePeopleDataSource(
        source: ApolloRemotePeopleDataSource
    ) : RemotePeopleDataSource

    @Binds
    @MockedData
    internal abstract fun bindMockRemotePeopleDataSource(
        source: MockRemotePeopleDataSource
    ) : RemotePeopleDataSource
}
