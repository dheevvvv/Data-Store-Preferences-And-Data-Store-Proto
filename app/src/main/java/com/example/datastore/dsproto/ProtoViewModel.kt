package com.example.datastore.dsproto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ProtoViewModel(application: Application):AndroidViewModel(application) {
    private val repo = UserPreferencesRepository(application)
    val dataUser = repo.userPreferencesFlow.asLiveData()

    fun editData(nama:String, umur:Int) = viewModelScope.launch {
        repo.saveData(nama,umur)
    }

    fun deleteData() = viewModelScope.launch {
        repo.clearData()
    }

}