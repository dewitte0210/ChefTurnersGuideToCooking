package com.example.chefturnersguidetocooking.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeDishType(
    @ColumnInfo(name = "rid")
    val rid: Long,
    @ColumnInfo(name = "dtid")
    val dtid: Long
)
