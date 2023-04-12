package com.example.datastore.dspref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences


const val PREFERENCE_NAME = "preference"

class UserManager(private val context:Context) {

    private val Context.dataStore by preferencesDataStore(PREFERENCE_NAME)

    private val NAME = stringPreferencesKey("user_name")
    private val AGE = intPreferencesKey("user_age")

    suspend fun saveData(name:String, age:Int){
        context.dataStore.edit {
            it[NAME] = name
            it[AGE] = age
        }
    }

    //    UNTUK CLEAR DATA YANG ADA DI DATASTORE PREFERENCES
    suspend fun clearData(){
        context.dataStore.edit {
            it.clear()
        }
    }

    //    Create an age flow to retrieve age from the preferences
    val userAgeFlow : Flow<Int> = context.dataStore.data.map {
        it[AGE] ?: 0
    }

    val userNameFlow: Flow<String> = context.dataStore.data.map {
        it[NAME] ?: ""
    }


}