package com.example.chefturnersguidetocooking.database

import kotlinx.coroutines.flow.Flow

class DatabaseRepository (
    val ingredientDao: IngredientDao,
    val measurementDao: MeasurementDao,
    val recipeDao: RecipeDao,
    val recipeIngredientDao: RecipeIngredientDao,
    val recipeDishTypeDao: RecipeDishTypeDao,
    val dishTypeDao: DishTypeDao,
    val singleRecipeDao: SingleRecipeAllInfoDao
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

    //Recipe Dish Type Queries
    override fun getAllRecipeDishTypes(): Flow<List<RecipeDishType>>
    = recipeDishTypeDao.getAllRecipeDishTypes()
    override suspend fun insertRecipeDishType(recipeDishType: RecipeDishType)
    = recipeDishTypeDao.insertRecipeDishType(recipeDishType)
    override suspend fun deleteRecipeDishType(recipeDishType: RecipeDishType)
    = recipeDishTypeDao.deleteRecipeDishType(recipeDishType)

    //DishType Queries
    override fun getAllTypes(): Flow<List<DishType>>
    = dishTypeDao.getAllTypes()
    override fun getType(typeName: String): Flow<List<DishType>>
    = dishTypeDao.getType(typeName)
    override suspend fun insertType(dishType: DishType)
    = dishTypeDao.insertType(dishType)
    override suspend fun deleteType(dishType: DishType)
    = dishTypeDao.deleteType(dishType)

    // Query Single Recipe
    override fun getSingleRecipe(recipeID: Long): Flow<SingleRecipeAllInfo>
       = singleRecipeDao.getSingleRecipe(recipeID)
    companion object {
        private var repository: DatabaseRepositoryInterface? = null
        fun getRepository(recipeDatabase: RecipeDatabase):
                DatabaseRepositoryInterface {
            if (repository == null) {
                repository = DatabaseRepository(
                    recipeDatabase.IngredientDao(),
                    recipeDatabase.MeasurementDao(),
                    recipeDatabase.RecipeDao(),
                    recipeDatabase.RecipeIngredientDao(),
                    recipeDatabase.RecipeDishTypeDao(),
                    recipeDatabase.DishTypeDao(),
                    recipeDatabase.SingleRecipeAllInfoDao()
                ) }
            return repository!!
        } }
}