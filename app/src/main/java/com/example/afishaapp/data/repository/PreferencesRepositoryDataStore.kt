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

private val NAME_FIELD = stringPreferencesKey("UserName")
private val EMAIL_FIELD = stringPreferencesKey("Email")
private val ENTRY_STATE_FIELD = booleanPreferencesKey("EntryState")
private val CITY_FIELD = stringPreferencesKey("DefaultCity")
private val AVATAR_FIELD = stringPreferencesKey("AvatarUrl")
private val LOCATION_SLUG_FIELD = stringPreferencesKey("LocationSlug")

class PreferencesRepositoryDataStore @Inject constructor(
    private val context: Context
): PreferencesRepository {
    private val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = NAME_STORE)

    override suspend fun setName(text: String) {
        context.datastore.edit { settings ->
            settings[NAME_FIELD] = text
        }
    }

    override suspend fun setEntryState(state: Boolean) {
        context.datastore.edit { settings ->
            settings[ENTRY_STATE_FIELD] = state
        }
    }

    override suspend fun setEmail(text: String) {
        context.datastore.edit { settings ->
            settings[EMAIL_FIELD] = text
        }
    }

    override suspend fun setAvatarUrl(text: String) {
        context.datastore.edit { settings ->
            settings[AVATAR_FIELD] = text
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

    override fun getName(): Flow<String> {
        return context.datastore.data.map {
            it[NAME_FIELD] ?: DEFAULT_RESPONSE
        }
    }

    override fun getEntryState(): Flow<Boolean> {
        return context.datastore.data.map {
            it[ENTRY_STATE_FIELD] ?: false
        }
    }

    override fun getEmail(): Flow<String> {
        return context.datastore.data.map {
            it[EMAIL_FIELD] ?: DEFAULT_RESPONSE
        }
    }

    override fun getAvatar(): Flow<String> {
        return context.datastore.data.map {
            it[AVATAR_FIELD] ?: DEFAULT_RESPONSE
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
}