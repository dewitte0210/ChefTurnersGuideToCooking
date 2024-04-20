package com.example.chefturnersguidetocooking.database

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SingleRecipeAllInfo(
    @Embedded
    val recipe: Recipe?,

    @Relation(
        parentColumn = "rid",
        entity = Ingredient::class,
        entityColumn = "iid",
        associateBy = Junction(
            value = RecipeIngredient::class,
            parentColumn = "rid",
            entityColumn = "iid"
        )
    )
    val ingredients: List<Ingredient>?,

    @Relation(
       parentColumn = "iid",
        entity = Measurement::class,
        entityColumn = "mid",
        associateBy = Junction(
            value = RecipeIngredient::class,
            parentColumn = "iid",
            entityColumn = "mid"
        )
    )
    val measurements: List<Measurement>?,

    @Relation(
        parentColumn = "rid",
        entity = DishType::class,
        entityColumn = "dtid",
        associateBy = Junction(
            value = RecipeDishType::class,
            parentColumn = "rid",
            entityColumn = "dtid"
        )
    )
    val dishTypes: List<DishType>?
)