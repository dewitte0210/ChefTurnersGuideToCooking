package com.example.chefturnersguidetocooking.database

data class DatabaseState(
    val recipes: List<Recipe> = listOf(),
    val ingredients: List<Ingredient> = listOf(),
    val measurements: List<Measurement> = listOf()
)
