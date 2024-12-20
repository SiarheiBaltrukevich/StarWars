package com.fanatics.codechallenge.data.datasource.chosenperson

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * keep the last chosen person id locally.
 */
internal class LocalChosenPersonIdDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    val personFlow: Flow<Long?> = dataStore.data.map { preferences ->
        preferences[KEY_CHOSEN_PERSON_ID]?.toLongOrNull()
    }

    suspend fun update(id: Long?) {
        id?.let {
            dataStore.edit { preferences -> preferences[KEY_CHOSEN_PERSON_ID] = id.toString() }
        } ?: clear()
    }

    suspend fun clear() {
        dataStore.edit { preferences -> preferences.remove(KEY_CHOSEN_PERSON_ID) }
    }

    companion object {
        val KEY_CHOSEN_PERSON_ID = stringPreferencesKey("key_chosen_person_id")
    }
}
