package com.example.chefturnersguidetocooking.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(tableName="RecipeIngredient",
    primaryKeys = ["rid", "iid"],
    foreignKeys = [
        ForeignKey(entity = Recipe::class,
            parentColumns = ["rid"],
            childColumns = ["rid"]),
        ForeignKey(entity = Ingredient::class,
            parentColumns = ["iid"],
            childColumns = ["iid"]),
        ForeignKey(entity = Measurement::class,
            parentColumns = ["mid"],
            childColumns = ["mid"])
    ]
)
data class RecipeIngredient(
    val rid: Long,
    val iid: Long,
    val mid: Long?,
    @ColumnInfo(name = "amount")
    val amount: Double?,
    @ColumnInfo(name = "prepared")
    val prepared: String?
)
