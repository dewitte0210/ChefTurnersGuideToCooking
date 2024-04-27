package com.example.chefturnersguidetocooking.database

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = ["measurementName"], unique = true)])
data class Measurement(
    @NonNull
    @PrimaryKey(autoGenerate = true)
        val mid: Long = 0,
    @ColumnInfo(name = "measurementName")
        val name: String?
)
