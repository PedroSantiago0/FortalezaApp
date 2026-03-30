package com.fortaleza.app.ui.activities

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.fortaleza.app.data.db.AppDatabase
import com.fortaleza.app.data.db.RecordRepository
import com.fortaleza.app.data.model.Record
import kotlinx.coroutines.launch

class RecordViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: RecordRepository
    val allRecords: LiveData<List<Record>>

    init {
        val dao = AppDatabase.getDatabase(application).recordDao()
        repository = RecordRepository(dao)
        allRecords = repository.allRecords
    }

    fun insert(record: Record) = viewModelScope.launch {
        repository.insert(record)
    }

    fun delete(record: Record) = viewModelScope.launch {
        repository.delete(record)
    }
}
