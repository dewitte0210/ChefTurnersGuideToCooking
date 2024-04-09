/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.chefturnersguidetocooking.data

import com.example.chefturnersguidetocooking.R
import com.example.chefturnersguidetocooking.model.Recipes

/**
 * Recipe data
 */
object ExamplesDataProvider{
    val defaultRecipe = getRecipesData()[0]

    fun getRecipesData(): List<Recipes> {
        return listOf(
            Recipes(
                id = 1,
                titleResourceId = R.string.testing,
                subtitleResourceId = R.string.recipe_list_subtitle,
                imageResourceId = R.drawable.brochette,
                recipeImageBanner = R.drawable.brochette,
                recipesDetails = R.string.recipe_detail_text
            ),
            Recipes(
                id = 2,
                titleResourceId = R.string.testing,
                subtitleResourceId = R.string.recipe_list_subtitle,
                imageResourceId = R.drawable.pancakes,
                recipeImageBanner = R.drawable.pancakes,
                recipesDetails = R.string.recipe_detail_text
            ),
            Recipes(
                id = 3,
                titleResourceId = R.string.testing,
                subtitleResourceId = R.string.recipe_list_subtitle,
                imageResourceId = R.drawable.pizza,
                recipeImageBanner = R.drawable.pizza,
                recipesDetails = R.string.recipe_detail_text
            ),
            Recipes(
                id = 4,
                titleResourceId = R.string.testing,
                subtitleResourceId = R.string.recipe_list_subtitle,
                imageResourceId = R.drawable.pokebowl,
                recipeImageBanner = R.drawable.pokebowl,
                recipesDetails = R.string.recipe_detail_text
            ),
            Recipes(
                id = 5,
                titleResourceId = R.string.testing,
                subtitleResourceId = R.string.recipe_list_subtitle,
                imageResourceId = R.drawable.chef,
                recipeImageBanner = R.drawable.chef,
                recipesDetails = R.string.recipe_detail_text
            ),
            Recipes(
                id = 6,
                titleResourceId = R.string.testing,
                subtitleResourceId = R.string.recipe_list_subtitle,
                imageResourceId = R.drawable.chef,
                recipeImageBanner = R.drawable.chef,
                recipesDetails = R.string.recipe_detail_text
            ),
            Recipes(
                id = 7,
                titleResourceId = R.string.testing,
                subtitleResourceId = R.string.recipe_list_subtitle,
                imageResourceId = R.drawable.chef,
                recipeImageBanner = R.drawable.chef,
                recipesDetails = R.string.recipe_detail_text
            )
        )
    }
}
