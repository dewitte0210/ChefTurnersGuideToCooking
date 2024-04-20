package com.example.chefturnersguidetocooking.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["name"], unique = true)])
data class Measurement(
    @PrimaryKey(autoGenerate = true)
        val mid: Long = 0,
    @ColumnInfo(name = "name")
        val name: String?
)
