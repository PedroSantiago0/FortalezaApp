package com.fortaleza.app.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.fortaleza.app.data.model.Record

@Dao
interface RecordDao {

    @Query("SELECT * FROM records ORDER BY id DESC")
    fun getAllRecords(): LiveData<List<Record>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(record: Record): Long

    @Delete
    suspend fun delete(record: Record)

    @Query("DELETE FROM records")
    suspend fun deleteAll()
}
