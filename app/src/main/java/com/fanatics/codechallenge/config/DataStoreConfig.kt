package com.fanatics.codechallenge.config

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private const val DATA_STORE_FILE_NAME = "app_data"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_FILE_NAME)
