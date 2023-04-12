package com.example.datastore.dsproto

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.dataStore
import com.example.datastore.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch


class UserPreferencesRepository(private val context: Context) {
    private val Context.userPreferencesStore: DataStore<UserPreferences> by dataStore(
        fileName = "user_prefs.pb",
        serializer = UserPreferencesSerializer
    )

    suspend fun saveData(nama:String, umur:Int){
        context.userPreferencesStore.updateData {
            it.toBuilder().setNama(nama).build()
            it.toBuilder().setUmur(umur.toString()).build()
        }
    }

    suspend fun clearData(){
        context.userPreferencesStore.updateData {
            it.toBuilder().clearNama().build()
            it.toBuilder().clearUmur().build()
        }
    }

    val userPreferencesFlow: Flow<UserPreferences> = context.userPreferencesStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(TAG, "Error reading sort order preferences.", exception)
                emit(UserPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }
}