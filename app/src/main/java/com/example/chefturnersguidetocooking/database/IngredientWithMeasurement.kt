package com.example.chefturnersguidetocooking.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class IngredientWithMeasurement(
    @Embedded
    val ingredient: Ingredient,
    @Relation(
        parentColumn = "iid",
        entity = Measurement::class,
        entityColumn = "mid",
        associateBy = Junction(
            value= RecipeIngredient::class,
            parentColumn = "iid",
            entityColumn = "mid"
        )
    )
    val measurement: Measurement
)
