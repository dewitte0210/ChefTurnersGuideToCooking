package com.example.chefturnersguidetocooking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import com.example.chefturnersguidetocooking.database.DatabaseRepository
import com.example.chefturnersguidetocooking.database.DatabaseViewModel
import com.example.chefturnersguidetocooking.database.RecipeDatabase
import com.example.chefturnersguidetocooking.ui.theme.ChefTurnersGuideToCookingTheme
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.chefturnersguidetocooking.RecipeApp
import com.example.chefturnersguidetocooking.ui.theme.RecipeTheme

/**
 * Activity for Recipes app
 */
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val database = RecipeDatabase.getInstance(applicationContext)
        val repository = DatabaseRepository.getRepository(database)
        val dbViewModel = DatabaseViewModel(repository)
        setContent {
            RecipeTheme {
                val layoutDirection = LocalLayoutDirection.current
                Surface(
                    modifier = Modifier
                        .padding(
                            start = WindowInsets.safeDrawing.asPaddingValues()
                                .calculateStartPadding(layoutDirection),
                            end = WindowInsets.safeDrawing.asPaddingValues()
                                .calculateEndPadding(layoutDirection)
                        )
                ) {
                    val navController = rememberNavController()
                    val onBackPressed: () -> Unit = {
                        // Define your logic for back button press here
                    }
                    val windowSize: WindowWidthSizeClass = WindowWidthSizeClass.Compact // Define your logic for windowSize here
                    RecipeApp(
                        onBackPressed = onBackPressed,
                        windowSize = windowSize,
                        navController = navController
                    )
                }
            }
        }
    }
}