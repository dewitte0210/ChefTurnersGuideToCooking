package com.example.chefturnersguidetocooking.database

import android.graphics.Bitmap
import android.media.Image
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.sql.Blob

class DatabaseViewModel(
    repository: DatabaseRepositoryInterface
) : ViewModel() {
    private val dbRepository: DatabaseRepositoryInterface

    init{
        dbRepository = repository
    }

    private val _dbState = MutableStateFlow(DatabaseState())
    private val _recipes = dbRepository.getAllRecipes().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = listOf()
    )
    private val _ingredients = dbRepository.getAllIngredients().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = listOf()
    )
    private val _measurements = dbRepository.getAllMeasurements().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = listOf()
    )
    private val _dishTypes = dbRepository.getAllRecipeDishTypes().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = listOf()
    )
    private val _curRid = MutableStateFlow(_dbState.value.curRid)

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _curRecipe = _curRid.flatMapLatest{ curRid ->
        dbRepository.getSingleRecipe(curRid)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = SingleRecipeAllInfo(null,null,null)
    )
    val dbState: StateFlow<DatabaseState> = combine(
        combine( _recipes, _ingredients, _measurements, ::Triple),
        combine(_dishTypes, _curRid, _curRecipe, ::Triple),
        _dbState)
    {t1, t2, dbState->
        dbState.copy(
            recipes = t1.first,
            ingredients = t1.second,
            measurements = t1.third,
            dishTypes = t2.first,
            curRid = t2.second,
            curRecipe = t2.third
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = DatabaseState()
    )

    //Inserts
    fun insertRecipe(name: String, origin: String, favorite: Boolean, image: Bitmap,
                     numCooked: Int, description: String, instructions: String, calories: Int,
                     carbs: Int, fat: Int, protein: Int, servings: Int, prepTime: String, cookTime: String, totalTime: String){
        val recipe = Recipe(name = name, origin = origin, favorite = favorite, image = image,
            numCooked = numCooked, description = description, instructions = instructions,
            calories = calories, carbs = carbs, fat = fat, protein = protein, servings = servings, prepTime = prepTime, cookTime = cookTime, totalTime = totalTime)
        viewModelScope.launch{
            dbRepository.insertRecipe(recipe)
        }
    }

    fun insertIngredient(name: String) {
        val ingredient = Ingredient(name = name)
        viewModelScope.launch{
            dbRepository.insertIngredient(ingredient)
        }
    }

    fun insertMeasurement(name: String){
        val measurement = Measurement(name = name)
        viewModelScope.launch{
            dbRepository.insertMeasurement(measurement)
        }
    }

    fun updateCurRid(id: Long){
        _curRid.value = id
    }

    fun toggleFavoriteRecipe(id: Long, fav: Boolean) {
        viewModelScope.launch {
            dbRepository.updateFav(fav, id)
        }
    }
   /*
    fun insertDishType(name: String){
        val dishType = RecipeDishType(name = name)
        viewModelScope.launch {
            dbRepository.insertRecipeDishType(dishType)
        }
    }
*/
}