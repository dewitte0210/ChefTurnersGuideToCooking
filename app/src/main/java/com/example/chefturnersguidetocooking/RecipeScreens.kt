package com.example.chefturnersguidetocooking

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.chefturnersguidetocooking.data.ExamplesDataProvider
import com.example.chefturnersguidetocooking.model.Recipes
import com.example.chefturnersguidetocooking.ui.theme.RecipeTheme
import com.example.chefturnersguidetocooking.RecipeContentType
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import com.example.chefturnersguidetocooking.RecipeViewModel
import androidx.navigation.compose.rememberNavController
import com.example.chefturnersguidetocooking.database.DatabaseViewModel
import com.example.chefturnersguidetocooking.database.Recipe
import com.example.chefturnersguidetocooking.database.SingleRecipeAllInfo
import com.example.chefturnersguidetocooking.ui.theme.md_theme_light_primary


/**
 * Main composable that serves as container
 * which displays content according to [uiState] and [windowSize]
 */
@Composable
fun RecipeApp(
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
    navController: NavController,
    dbViewModel: DatabaseViewModel
) {
    val viewModel: RecipeViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact,
        WindowWidthSizeClass.Medium -> RecipeContentType.ListOnly

        WindowWidthSizeClass.Expanded -> RecipeContentType.ListAndDetail
        else -> RecipeContentType.ListOnly
    }
    val dbState = dbViewModel.dbState.collectAsState()
    Scaffold(
        topBar = {
            RecipeAppBar(
                isShowingListPage = uiState.isShowingListPage,
                onBackButtonClick = { viewModel.navigateToListPage() },
                windowSize = windowSize
            )
        },
        bottomBar = {
            BottomNavigation(navController = navController)
        }
    ) { innerPadding ->
        if (contentType == RecipeContentType.ListAndDetail) {
            RecipeListAndDetail(
                recipes = dbState.value.recipes,
                selectedRecipe = dbState.value.curRecipe!!,
                onClick = {
                   dbViewModel.updateCurRid(it.rid)
                },
                onBackPressed = onBackPressed,
                contentPadding = innerPadding,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            if (uiState.isShowingListPage) {
                RecipesList(
                    recipes = dbState.value.recipes,
                    onClick = {
                        dbViewModel.updateCurRid(it.rid)
                        viewModel.navigateToDetailPage()
                    },
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                    contentPadding = innerPadding,
                )
            } else {
                RecipesDetail(
                    selectedRecipe = dbState.value.curRecipe,
                    contentPadding = innerPadding,
                    onBackPressed = {
                        viewModel.navigateToListPage()
                    }
                )
            }
        }
    }
}
/**
 * Composable that displays the topBar and displays back button if back navigation is possible.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeAppBar(
    onBackButtonClick: () -> Unit,
    isShowingListPage: Boolean,
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    var checked by remember { mutableStateOf(true) }
    val icon: (@Composable () -> Unit)? = if (checked) {
        {
            Icon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = stringResource(R.string.fav_button)
            )
        }
    } else {
        null
    }
    val isShowingDetailPage = windowSize != WindowWidthSizeClass.Expanded && !isShowingListPage
    TopAppBar(
        title = {
            Text(
                text =
                if (isShowingDetailPage) {
                    stringResource(R.string.detail_fragment_label)
                } else {
                    stringResource(R.string.list_fragment_label)
                },
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = if (isShowingDetailPage) {
            {
                IconButton(onClick = onBackButtonClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }

            }
        } else {
            { Box {} }
        },
        actions = if (isShowingDetailPage) {
            {
                Switch(
                    checked = checked,
                    onCheckedChange = { checked = it },
                    thumbContent = icon
                )
            }
        } else {
            { Box {} }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        modifier = modifier,
    )
}

@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    Surface(
        color = md_theme_light_primary,
        contentColor = contentColorFor(md_theme_light_primary),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf(
                BottomNavigationItem.Home,
                BottomNavigationItem.AddRecipes,
                BottomNavigationItem.Favorites
            ).forEach { item ->
                BottomNavigationItem(
                    item = item,
                    onClick = { navController.navigate(item.route) }
                )
            }
        }
    }
}

@Composable
fun BottomNavigationItem(
    item: BottomNavigationItem,
    onClick: () -> Unit
) {
    // Customize this based on your UI design
    Text(
        text = item.label,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp, horizontal = 16.dp)
    )
}


sealed class BottomNavigationItem(val route: String, val label: String) {
    object Home : BottomNavigationItem("home", "Home")
    object AddRecipes : BottomNavigationItem("add_recipes", "Add Recipes")
    object Favorites : BottomNavigationItem("favorites", "Favorites")

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RecipeListItem(
    recipe: Recipe,
    onItemClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(recipe) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            RecipeImageItem(
                recipe = recipe,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
                    .weight(1f)
            ) {
                Text(
                    text = recipe.recipeName ?: stringResource(R.string.noNameDefault),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
                /*
                Text( /// -------- Mark
                    text = stringResource(recipes.subtitleResourceId),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )*/
                Row {
                }
            }
        }
    }
}


@Composable
private fun RecipeImageItem(recipe: Recipe, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        if (recipe.image?.asImageBitmap() != null) {
            Image(
                painter =  BitmapPainter(recipe.image.asImageBitmap()),
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.FillWidth
            )
        } else {
            Image(
                painter = painterResource(R.drawable.chef),
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.FillWidth
            )
        }
    }
}

@Composable
private fun RecipesList(
    recipes: List<Recipe>,
    onClick: (Recipe) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_medium)),
    ) {
        items(recipes, key = { recipe -> recipe.rid }) { recipe ->
            RecipeListItem(
                recipe = recipe,
                onItemClick = onClick
            )
        }
    }
}
 // The Above function has been converted!
@Composable
private fun RecipesDetail(
    selectedRecipe: SingleRecipeAllInfo?,
    onBackPressed: () -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onBackPressed()
    }
    val scrollState = rememberScrollState()
    val layoutDirection = LocalLayoutDirection.current
    Box(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(top = contentPadding.calculateTopPadding())
    ) {
        Column(
            modifier = Modifier
                .padding(
                    bottom = contentPadding.calculateTopPadding(),
                    start = contentPadding.calculateStartPadding(layoutDirection),
                    end = contentPadding.calculateEndPadding(layoutDirection)
                )
        ) {
            Box {
                Box {
                    if (selectedRecipe?.recipe?.image?.asImageBitmap() != null) {
                        Image(
                            painter = BitmapPainter(selectedRecipe.recipe.image.asImageBitmap()),
                            contentDescription = null,
                            alignment = Alignment.TopCenter,
                            contentScale = ContentScale.FillWidth,
                        )
                    } else {
                        Image(
                            painter = painterResource(R.drawable.chef),
                            contentDescription = null,
                            alignment = Alignment.TopCenter,
                            contentScale = ContentScale.FillWidth,
                        )
                    }
                }
                Column(
                    Modifier
                        .align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .background(
                            Brush.verticalGradient(
                                listOf(Color.Transparent, MaterialTheme.colorScheme.scrim),
                                0f,
                                400f
                            )
                        )
                ) {
                    Text(
                        text = selectedRecipe?.recipe?.recipeName ?: "",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.inverseOnSurface,
                        modifier = Modifier
                            .padding(horizontal = dimensionResource(R.dimen.padding_small))
                    )
                    Row(
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    ) {
                    }
                }
            }
            Text(
                text = selectedRecipe?.recipe?.description ?: "",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    vertical = dimensionResource(R.dimen.padding_detail_content_vertical),
                    horizontal = dimensionResource(R.dimen.padding_detail_content_horizontal)
                )
            )
            Text(
                text = selectedRecipe?.recipe?.instructions ?: "",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    vertical = dimensionResource(R.dimen.padding_detail_content_vertical),
                    horizontal = dimensionResource( R.dimen.padding_detail_content_horizontal)
                )
            )
            Text(
                text = "Servings: " + (selectedRecipe?.recipe?.servings?: ""),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    vertical = dimensionResource(R.dimen.padding_small),
                    horizontal = dimensionResource( R.dimen.padding_detail_content_horizontal)
                )
            )
            Text(
                text = "Calories: " + (selectedRecipe?.recipe?.calories?: ""),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    vertical = dimensionResource(R.dimen.padding_small),
                    horizontal = dimensionResource( R.dimen.padding_detail_content_horizontal)
                )
            )
            Text(
                text = "Carbs: " + (selectedRecipe?.recipe?.carbs?: ""),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    vertical = dimensionResource(R.dimen.padding_small),
                    horizontal = dimensionResource( R.dimen.padding_detail_content_horizontal)
                )
            )
            Text(
                text = "Fat: " + (selectedRecipe?.recipe?.fat?: ""),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    vertical = dimensionResource(R.dimen.padding_small),
                    horizontal = dimensionResource( R.dimen.padding_detail_content_horizontal)
                )
            )
            Text(
                text = "Protein: " + (selectedRecipe?.recipe?.protein?: ""),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    vertical = dimensionResource(R.dimen.padding_small),
                    horizontal = dimensionResource( R.dimen.padding_detail_content_horizontal)
                )
            )
            Row(){
                // Needed to separate start and end padding to display two fields next to each other
                Text(
                    text = "Cook Time: " + (selectedRecipe?.recipe?.cookTime ?: ""),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(PaddingValues(
                        top = dimensionResource(R.dimen.padding_detail_content_vertical),
                        bottom = dimensionResource(R.dimen.padding_detail_content_vertical),
                        start = dimensionResource(R.dimen.padding_detail_content_horizontal),
                        end = dimensionResource(R.dimen.padding_small)
                    ))
                )
                Text(
                    text = "Prep Time: " + (selectedRecipe?.recipe?.prepTime ?: ""),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        PaddingValues(
                            top =dimensionResource(R.dimen.padding_detail_content_vertical),
                            bottom = dimensionResource(R.dimen.padding_detail_content_vertical),
                            start = dimensionResource(R.dimen.padding_small),
                            end = dimensionResource(R.dimen.padding_detail_content_horizontal)
                        )
                    )
                )
            }
        }
    }
}

@Composable
private fun RecipeListAndDetail(
    recipes: List<Recipe>,
    selectedRecipe: SingleRecipeAllInfo?,
    onClick: (Recipe) -> Unit,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Row(
        modifier = modifier
    ) {
        RecipesList(
            recipes = recipes,
            onClick = onClick,
            contentPadding = contentPadding, // Use contentPadding here
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )
        RecipesDetail(
            selectedRecipe = selectedRecipe,
            modifier = Modifier.weight(3f),
            contentPadding = contentPadding, // Use contentPadding here
            onBackPressed = onBackPressed,
        )
    }
}

/* Preview functions that dont work now that database is integrated
@Preview
@Composable
fun RecipesListItemPreview() {
    RecipeTheme {
        RecipeListItem(
            recipe = ExamplesDataProvider.defaultRecipe,
            onItemClick = {}
        )
    }
}

@Preview
@Composable
fun RecipesListPreview() {
    RecipeTheme {
        Surface {
            RecipesList(
                recipes = ExamplesDataProvider.getRecipesData(),
                onClick = {},
            )
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
fun RecipesListAndDetailsPreview() {
    RecipeTheme {
        Surface {
            RecipeListAndDetail(
                recipes = ExamplesDataProvider.getRecipesData(),
                selectedRecipes = ExamplesDataProvider.getRecipesData().getOrElse(0) {
                    ExamplesDataProvider.defaultRecipe
                },
                onClick = {},
                onBackPressed = {},
            )
        }
    }
}
*/