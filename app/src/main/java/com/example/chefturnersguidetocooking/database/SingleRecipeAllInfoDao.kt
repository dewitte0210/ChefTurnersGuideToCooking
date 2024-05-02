package com.example.chefturnersguidetocooking.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface SingleRecipeAllInfoDao {
    @Transaction
    @Query("SELECT r.name, r.origin, r.rid, r.favorite, r.image, r.num_cooked," +
            "r.description, r.instructions, r.servings, r.calories, r.carbs, r.fat, r.protein, r.prep_time, r.cook_time," +
            "r.total_time, i.name as ingredientName,m.name as measurementName, dt.name as typeName, " +
            "ri.amount, ri.prepared FROM Recipe r " +
            "JOIN RecipeIngredient ri ON ri.rid = r.rid " +
            "JOIN Ingredient i ON i.iid = ri.iid " +
            "JOIN Measurement m ON m.mid = ri.mid " +
            "JOIN RecipeDishType rdt ON rdt.rid = r.rid " +
            "JOIN DishType dt ON dt.dtid = rdt.dtid " +
            "WHERE r.rid = :recipeID")
    fun getSingleRecipe(recipeID: Long): Flow<SingleRecipeAllInfo>

}