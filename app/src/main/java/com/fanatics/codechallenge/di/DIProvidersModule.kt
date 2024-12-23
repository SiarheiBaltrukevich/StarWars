package com.fanatics.codechallenge.di

import com.apollographql.apollo.ApolloClient
import com.fanatics.codechallenge.StarWarsApp.Companion.APOLLO_SERVER_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIProvidersModule {

    @Provides
    fun providesCoroutineScope(): CoroutineScope =
        CoroutineScope(SupervisorJob() + Dispatchers.Default)

    @Provides
    @Singleton
    fun providesApolloClient(): ApolloClient = ApolloClient.builder()
        .serverUrl(APOLLO_SERVER_URL)
        .build()
}
