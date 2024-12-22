package com.fanatics.codechallenge.di

import com.fanatics.codechallenge.data.datasource.people.ApolloRemotePeopleDataSource
import com.fanatics.codechallenge.data.datasource.people.MockedRemotePeopleDataSource
import com.fanatics.codechallenge.data.datasource.people.RemotePeopleDataSource
import com.fanatics.codechallenge.data.datasource.person.ApolloRemotePersonDataSource
import com.fanatics.codechallenge.data.datasource.person.MockedRemotePersonDataSource
import com.fanatics.codechallenge.data.datasource.person.RemotePersonDataSource
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
        source: MockedRemotePeopleDataSource
    ) : RemotePeopleDataSource

    @Binds
    @Apollo
    internal abstract fun bindApolloRemotePersonDataSource(
        source: ApolloRemotePersonDataSource
    ) : RemotePersonDataSource

    @Binds
    @MockedData
    internal abstract fun bindMockRemotePersonDataSource(
        source: MockedRemotePersonDataSource
    ) : RemotePersonDataSource
}
