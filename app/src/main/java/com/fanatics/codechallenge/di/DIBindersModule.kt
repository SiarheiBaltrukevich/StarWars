package com.fanatics.codechallenge.di

import com.fanatics.codechallenge.data.datasource.people.ApolloRemotePeopleDataSource
import com.fanatics.codechallenge.data.datasource.people.MockedRemotePeopleDataSource
import com.fanatics.codechallenge.data.datasource.people.RemotePeopleDataSource
import com.fanatics.codechallenge.data.datasource.person.ApolloRemotePersonDataSource
import com.fanatics.codechallenge.data.datasource.person.MockedRemotePersonDataSource
import com.fanatics.codechallenge.data.datasource.person.RemotePersonDataSource
import com.fanatics.codechallenge.di.annotation.Apollo
import com.fanatics.codechallenge.di.annotation.MockedData
import com.fanatics.codechallenge.util.DispatcherProvider
import com.fanatics.codechallenge.util.DispatcherProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DIBindersModule {

    @Binds
    internal abstract fun bindDispatcherProvider(
        provider: DispatcherProviderImpl
    ) : DispatcherProvider

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
