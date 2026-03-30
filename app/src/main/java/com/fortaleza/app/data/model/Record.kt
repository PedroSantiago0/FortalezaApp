package com.fortaleza.app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "records")
data class Record(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val category: String,
    val description: String,
    val photoPath: String? = null,
    val date: String,
    val status: String = "pending" // "pending" | "done"
)
