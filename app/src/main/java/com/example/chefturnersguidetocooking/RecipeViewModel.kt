package com.example.chefturnersguidetocooking

import androidx.lifecycle.ViewModel
import com.example.chefturnersguidetocooking.data.ExamplesDataProvider
import com.example.chefturnersguidetocooking.model.Recipes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

/**
 * View Model for Sports app
 */

class RecipeViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        RecipeUiState(
            RecipeList = ExamplesDataProvider.getRecipesData(),
            currentRecipes = ExamplesDataProvider.getRecipesData().getOrElse(0) {
                ExamplesDataProvider.defaultRecipe
            }
        )
    )
    val uiState: StateFlow<RecipeUiState> = _uiState

    fun updateCurrentRecipe(selectedRecipes: Recipes) {
        _uiState.update {
            it.copy(currentRecipes = selectedRecipes)
        }
    }

    fun navigateToListPage() {
        _uiState.update {
            it.copy(isShowingListPage = true)
        }
    }


    fun navigateToDetailPage() {
        _uiState.update {
            it.copy(isShowingListPage = false)
        }
    }
}

data class RecipeUiState(
    val RecipeList: List<Recipes> = emptyList(),
    val currentRecipes: Recipes = ExamplesDataProvider.defaultRecipe,
    val isShowingListPage: Boolean = true
)


