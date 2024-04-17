package com.example.chefturnersguidetocooking

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chefturnersguidetocooking.RecipeApp
import com.example.chefturnersguidetocooking.ui.theme.RecipeTheme
import androidx.compose.material3.Scaffold

/**
 * Activity for Recipes app
 */
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            RecipeAppContent()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RecipeAppContent() {
    RecipeTheme {
        val layoutDirection = LocalLayoutDirection.current
        val navController = rememberNavController()

        Scaffold(
            modifier = Modifier.fillMaxSize(), // Ensure the Scaffold fills the entire screen
            content = {
                val onBackPressed: () -> Unit = {
                    // Define your logic for back button press here
                }
                val windowSize: WindowWidthSizeClass = WindowWidthSizeClass.Compact // Define your logic for windowSize here
                NavHost(navController = navController, startDestination = "home") {
                    composable("home") {
                        RecipeApp(
                            windowSize = WindowWidthSizeClass.Compact, // Define your logic for windowSize here
                            onBackPressed = onBackPressed,
                            navController = navController
                        )
                    }
                    composable("add_recipes") { AddingView() }
                    composable("favorites") { FavoritesView() }
                }
            },
            bottomBar = {
                BottomNavigation(navController = navController)
            }
        )
    }
}