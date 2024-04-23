package com.example.chefturnersguidetocooking

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chefturnersguidetocooking.ui.theme.RecipeTheme
import androidx.compose.ui.graphics.Color
import com.example.chefturnersguidetocooking.ui.theme.md_theme_light_onPrimary
import com.example.chefturnersguidetocooking.ui.theme.md_theme_light_onSurface
import com.example.chefturnersguidetocooking.ui.theme.md_theme_light_primary
import com.example.chefturnersguidetocooking.ui.theme.RecipeTheme
import com.example.chefturnersguidetocooking.ui.theme.md_theme_dark_errorContainer
import com.example.chefturnersguidetocooking.ui.theme.md_theme_dark_scrim


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesView() {
    RecipeTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.favorite_page),
                        fontWeight = FontWeight.Bold
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "There are no favorites yet.",
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = md_theme_dark_scrim
                )
            }
        }
    }
}