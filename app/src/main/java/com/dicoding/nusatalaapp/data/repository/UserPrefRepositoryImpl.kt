package com.dicoding.nusatalaapp.data.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import com.dicoding.nusatalaapp.domain.model.User
import com.dicoding.nusatalaapp.domain.repository.UserPrefRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_pref")

class UserPrefRepositoryImpl(
    context: Context
) : UserPrefRepository {
    private val dataStore = context.dataStore

    override suspend fun saveUserSession(userModel: User) {
        dataStore.edit { pref ->
            pref[idKey] = userModel.id!!
            pref[tokenKey] = userModel.token.toString()
            pref[nameKey] = userModel.name.toString()
            pref[photoKey] = userModel.photo.toString()
            pref[usernameKey] = userModel.username.toString()
            pref[emailKey] = userModel.email.toString()
        }
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
        return dataStore.data
            .catch { e ->
                if (e is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw e
                }
            }
            .map { pref ->
                val savedUser = User(
                    id = pref[idKey],
                    username = pref[usernameKey],
                    email = pref[emailKey],
                    name = pref[nameKey],
                    photo = pref[photoKey],
                    token = pref[tokenKey],
                )

                savedUser
            }
    }

    override suspend fun destroyUserSession(): Boolean {
        return try {
            dataStore.edit { pref ->
                pref.remove(idKey)
                pref.remove(usernameKey)
                pref.remove(emailKey)
                pref.remove(nameKey)
                pref.remove(photoKey)
                pref.remove(tokenKey)
            }
            true
        } catch (e: Exception) {
            false
        }
    }

    companion object {
        val onBoardingKey = booleanPreferencesKey(name = "on_boarding_done")
        val tokenKey = stringPreferencesKey(name = "token")
        val nameKey = stringPreferencesKey(name = "name")
        val photoKey = stringPreferencesKey(name = "photo")
        val idKey = intPreferencesKey(name = "id")
        val usernameKey = stringPreferencesKey(name = "username")
        val emailKey = stringPreferencesKey(name = "email")
    }
}