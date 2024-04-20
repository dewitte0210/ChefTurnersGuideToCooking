package com.example.chefturnersguidetocooking.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "RecipeDishType",
    primaryKeys = ["rid", "dtid"],
    foreignKeys = [
        ForeignKey(
            entity = Recipe::class,
            parentColumns = ["rid"],
            childColumns = ["rid"]
        ),
        ForeignKey(
            entity = DishType::class,
            parentColumns = ["dtid"],
            childColumns = ["dtid"]
        )
    ]
)
data class RecipeDishType(
    val rid: Long?,
    val dtid: Long?
)
