package com.fanatics.codechallenge.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.apollographql.apollo.ApolloClient
import com.fanatics.codechallenge.StarWarsApp.Companion.APOLLO_SERVER_URL
import com.fanatics.codechallenge.config.dataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DIModule {

    @Provides
    @Singleton
    fun providesApolloClient(): ApolloClient = ApolloClient.Builder()
        .serverUrl(APOLLO_SERVER_URL)
        .build()

    @Singleton
    @Provides
    fun provideDataStorePreferences(
        @ApplicationContext context: Context
    ): DataStore<Preferences> =
        context.dataStore
}
