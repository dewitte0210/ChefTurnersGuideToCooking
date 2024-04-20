package com.example.chefturnersguidetocooking.database

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SingleRecipeAllInfoDao {

    @Query("SELECT * FROM Recipe r " +
            "JOIN RecipeIngredient ri ON ri.rid = r.rid " +
            "JOIN Ingredient i ON i.iid = ri.iid " +
            "JOIN Measurement m ON m.mid = ri.mid " +
            "JOIN RecipeDishType rdt ON rdt.rid = r.rid " +
            "JOIN DishType dt ON dt.dtid = rdt.dtid " +
            "WHERE r.rid = :recipeID")
    fun getSingleRecipe(recipeID: Long): Flow<SingleRecipeAllInfo>
}