package com.dicoding.nusatalaapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.dicoding.nusatalaapp.domain.model.User
import com.dicoding.nusatalaapp.domain.repository.UserPrefRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_pref")

class UserPrefRepositoryImpl(
    context: Context
) : UserPrefRepository {

    private val dataStore = context.dataStore

    override suspend fun saveUserSession(userModel: User) {

    }

    override suspend fun saveOnBoardingState(state: Boolean) {
        dataStore.edit { pref ->
            pref[onBoardingKey] = state
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { e ->
                if (e is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw e
                }
            }
            .map { pref ->
                val onBoardingState = pref[onBoardingKey] ?: false
                onBoardingState
            }
    }

    override fun readUserSession(): Flow<User> {
        return flowOf(User(1))
    }

    override suspend fun destroyUserSession(): Boolean {
        return true
    }

    companion object {
        val onBoardingKey = booleanPreferencesKey(name = "on_boarding_done")
    }
}