package com.example.afishaapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.afishaapp.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val NAME_STORE = "User_settings"
private const val DEFAULT_RESPONSE = "None"

private val ENTRY_STATE_FIELD = booleanPreferencesKey("EntryState")
private val CITY_FIELD = stringPreferencesKey("DefaultCity")
private val LOCATION_SLUG_FIELD = stringPreferencesKey("LocationSlug")
private val THEME_FIELD = booleanPreferencesKey("theme_field")

class PreferencesRepositoryDataStore @Inject constructor(
    private val context: Context
): PreferencesRepository {
    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = NAME_STORE)

    override suspend fun setEntryState(state: Boolean) {
        context.datastore.edit { settings ->
            settings[ENTRY_STATE_FIELD] = state
        }
    }

    override suspend fun setDefaultCity(text: String) {
        context.datastore.edit { settings ->
            settings[CITY_FIELD] = text
        }
    }

    override suspend fun setLocationSlug(text: String) {
        context.datastore.edit { settings ->
            settings[LOCATION_SLUG_FIELD] = text
        }
    }

    override suspend fun setDarkTheme(state: Boolean) {
        context.datastore.edit { setting ->
            setting[THEME_FIELD] = state
        }
    }

    override fun getEntryState(): Flow<Boolean> {
        return context.datastore.data.map {
            it[ENTRY_STATE_FIELD] ?: false
        }
    }

    override fun getDefaultCity(): Flow<String> {
        return context.datastore.data.map {
            it[CITY_FIELD] ?: DEFAULT_RESPONSE
        }
    }

    override fun getLocationSlug(): Flow<String> {
        return context.datastore.data.map {
            it[LOCATION_SLUG_FIELD] ?: DEFAULT_RESPONSE
        }
    }

    override fun getDarkTheme(): Flow<Boolean> {
        return context.datastore.data.map {
            it[THEME_FIELD] ?: true
        }
    }
}