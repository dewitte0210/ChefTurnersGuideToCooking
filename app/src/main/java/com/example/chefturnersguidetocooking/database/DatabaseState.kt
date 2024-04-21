package com.example.chefturnersguidetocooking.database

data class DatabaseState(
    val recipes: List<Recipe> = listOf(),
    val ingredients: List<Ingredient> = listOf(),
    val measurements: List<Measurement> = listOf(),
    val dishTypes: List<RecipeDishType> = listOf(),
    val curRid: Long = 0,
    val curRecipe: SingleRecipeAllInfo? = null
)