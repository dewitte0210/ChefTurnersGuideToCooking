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
    suspend fun insertRecipe(rec: Recipe)
    suspend fun deleteRecipe(rec: Recipe)

    //RecipeIngredient Queries
    fun getAllRecipeIngredients(): Flow<List<RecipeIngredient>>
    suspend fun insertRecipeIngredient(recIng: RecipeIngredient)
    suspend fun deleteRecipeIngredient(recIng: RecipeIngredient)

    //RecipeDishType Queries
    fun getAllRecipeDishTypes(): Flow<List<RecipeDishType>>
    suspend fun insertRecipeDishType(recipeDishType: RecipeDishType)
    suspend fun deleteRecipeDishType(recipeDishType: RecipeDishType)

    //DishType Queries
    fun getAllTypes(): Flow<List<DishType>>
    fun getType(typeName: String): Flow<List<DishType>>
    suspend fun insertType(dishType: DishType)
    suspend fun deleteType(dishType: DishType)

    fun getSingleRecipe(recipeID: Long): Flow<SingleRecipeAllInfo>
}