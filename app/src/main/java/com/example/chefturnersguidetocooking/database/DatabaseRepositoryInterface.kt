package com.example.chefturnersguidetocooking.database

import kotlinx.coroutines.flow.Flow

interface DatabaseRepositoryInterface {
    //Ingredient Queries
    fun getAllIngredients(): Flow<List<Ingredient>>
    fun getIngredient(ingredientName: String): Flow<List<Ingredient>>
    suspend fun insertIngredient(ingr: Ingredient)
    suspend fun deleteIngredient(ingr: Ingredient)

    //Measurement Queries
    fun getAllMeasurements(): Flow<List<Measurement>>
    fun getMeasurement(measurementName: String): Flow<List<Measurement>>
    suspend fun insertMeasurement(meas: Measurement)
    suspend fun deleteMeasurement(meas: Measurement)

    //Recipe Queries
    fun getAllRecipes(): Flow<List<Recipe>>
    fun getRecipe(recipeID: Long): Flow<List<Recipe>>
    suspend fun insertRecipe(rec: Recipe)
    suspend fun deleteRecipe(rec: Recipe)

    //RecipeIngredient Queries
    fun getAllRecipeIngredients(): Flow<List<RecipeIngredient>>
    suspend fun insertRecipeIngredient(recIng: RecipeIngredient)
    suspend fun deleteRecipeIngredient(recIng: RecipeIngredient)

    //DishType Queries
    fun getAllRecipeDishTypes(): Flow<List<RecipeDishType>>
    suspend fun insertRecipeDishType(dishType: RecipeDishType)
    suspend fun deleteRecipeDishType(dishType: RecipeDishType)
}