package com.example.chefturnersguidetocooking.database

import kotlinx.coroutines.flow.Flow

class DatabaseRepository (
    val ingredientDao: IngredientDao,
    val measurementDao: MeasurementDao,
    val recipeDao: RecipeDao,
    val recipeIngredientDao: RecipeIngredientDao
): DatabaseRepositoryInterface {
    //Ingredient Queries
    override fun getAllIngredients(): Flow<List<Ingredient>>
    = ingredientDao.getAllIngredients()
    override fun getIngredient(ingredientName: String): Flow<List<Ingredient>>
    = ingredientDao.getIngredient(ingredientName)
    override suspend fun insertIngredient(ingr: Ingredient)
    = ingredientDao.insertIngredient(ingr)
    override suspend fun deleteIngredient(ingr: Ingredient)
    = ingredientDao.deleteIngredient(ingr)

    //Measurement Queries
    override fun getAllMeasurements(): Flow<List<Measurement>>
    = measurementDao.getAllMeasurements()
    override fun getMeasurement(measurementName: String): Flow<List<Measurement>>
    = measurementDao.getMeasurement(measurementName)
    override suspend fun insertMeasurement(meas: Measurement)
    = measurementDao.insertMeasurement(meas)
    override suspend fun deleteMeasurement(meas: Measurement)
    = measurementDao.deleteMeasurement(meas)

    //Recipe Queries
    override fun getAllRecipes(): Flow<List<Recipe>>
    = recipeDao.getAllRecipes()
    override fun getRecipe(recipeID: Long): Flow<List<Recipe>>
    = recipeDao.getRecipe(recipeID)
    override suspend fun insertRecipe(rec: Recipe)
    = recipeDao.insertRecipe(rec)
    override suspend fun deleteRecipe(rec: Recipe)
    = recipeDao.deleteRecipe(rec)

    //RecipeIngredient Queries
    override fun getAllRecipeIngredients(): Flow<List<RecipeIngredient>>
    = recipeIngredientDao.getAllRecipeIngredients()
    override suspend fun insertRecipeIngredient(recIng: RecipeIngredient)
    = recipeIngredientDao.insertRecipeIngredient(recIng)
    override suspend fun deleteRecipeIngredient(recIng: RecipeIngredient)
    = recipeIngredientDao.deleteRecipeIngredient(recIng)

    companion object {
        private var repository: DatabaseRepositoryInterface? = null
        fun getRepository(recipeDatabase: RecipeDatabase):
                DatabaseRepositoryInterface {
            if (repository == null) {
                repository = DatabaseRepository(
                    recipeDatabase.IngredientDao(),
                    recipeDatabase.MeasurementDao(),
                    recipeDatabase.RecipeDao(),
                    recipeDatabase.RecipeIngredientDao()
                ) }
            return repository!!
        } }
}