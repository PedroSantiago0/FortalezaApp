package com.fortaleza.app.data.db

import androidx.lifecycle.LiveData
import com.fortaleza.app.data.model.Record

class RecordRepository(private val dao: RecordDao) {

    val allRecords: LiveData<List<Record>> = dao.getAllRecords()

    suspend fun insert(record: Record): Long = dao.insert(record)

    suspend fun delete(record: Record) = dao.delete(record)
}
