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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
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
    dbViewModel: DatabaseViewModel,
    displayFavorite: Boolean
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
        Image(
            painter = painterResource(id = R.drawable.backg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            alpha = 0.6f
        )
        if (contentType == RecipeContentType.ListAndDetail) {
            RecipeListAndDetail(
                recipes = dbState.value.recipes,
                selectedRecipe = dbState.value.curRecipe!!,
                onClick = {
                   dbViewModel.updateCurRid(it.rid)
                },
                onBackPressed = onBackPressed,
                contentPadding = innerPadding,
                dbViewModel = dbViewModel,
                displayFavorite = displayFavorite,
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
                    displayFavorite = displayFavorite
                )
            } else {
                RecipesDetail(
                    selectedRecipe = dbState.value.curRecipe,
                    contentPadding = innerPadding,
                    onBackPressed = {
                        viewModel.navigateToListPage()
                    },
                    dbViewModel = dbViewModel
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
            .fillMaxWidth()
            .height(72.dp) // Increase the height to 72.dp or any value you prefer
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

    item.icon(onClick)
}

sealed class BottomNavigationItem(val route: String, val icon: @Composable (onClick: () -> Unit) -> Unit) {
    object Home : BottomNavigationItem("home", { onClick ->
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(46.dp) // Increase the size of the icon
        ) {
            Icon(Icons.Filled.Home, contentDescription = "Home")
        }
    })

    object AddRecipes : BottomNavigationItem("add_recipes", { onClick ->
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(46.dp) // Increase the size of the icon
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add Recipes")
        }
    })

    object Favorites : BottomNavigationItem("favorite", { onClick ->
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(46.dp) // Increase the size of the icon
        ) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favorites")
        }
    })
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
                    text = recipe.name ?: stringResource(R.string.noNameDefault),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = (recipe.servings?.toString() ?: stringResource(R.string.noNameDefault))+" "+(stringResource(R.string.servings_text)),
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(Modifier.size(dimensionResource(R.dimen.padding_small)))
                    Text(
                        text = (recipe.calories?.toString() ?: stringResource(R.string.noNameDefault))+" "+(stringResource(R.string.calories_text)),
                        style = MaterialTheme.typography.titleSmall
                    )
                }
                Text(
                    text = (stringResource(R.string.prep_time_text))+" "+(recipe.prepTime ?: stringResource(R.string.noNameDefault)),
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = (stringResource(R.string.cook_time_text))+" "+(recipe.cookTime ?: stringResource(R.string.noNameDefault)),
                    style = MaterialTheme.typography.titleSmall
                )
                Text(
                    text = (stringResource(R.string.total_time_text))+" "+(recipe.totalTime ?: stringResource(R.string.noNameDefault)),
                    style = MaterialTheme.typography.titleSmall
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
        modifier = modifier.fillMaxHeight()
    ) {
        if (recipe.image?.asImageBitmap() != null) {
            Image(
                painter =  BitmapPainter(recipe.image.asImageBitmap()),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            )
        } else {
            Image(
                painter = painterResource(R.drawable.chef),
                contentDescription = null,
                alignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
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
    displayFavorite: Boolean
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_medium)),
    ) {
        items(recipes, key = { recipe -> recipe.rid }) { recipe ->
            if(displayFavorite && recipe.favorite) {
                RecipeListItem(
                    recipe = recipe,
                    onItemClick = onClick
                )
            } else if (!displayFavorite){
                RecipeListItem(
                    recipe = recipe,
                    onItemClick = onClick
                )
            }
        }
    }
}

/**
 * Composable function that lays out the details of each recipe
 * @param selectedRecipe (SingleRecipeAllInfo?) The object from the database that holds the recipe data
 *      view the SingleRecipeAllInfo page to view all fields you can pull from there
 * @param onBackPressed (fun -> Unit()) function to handle pressing the back button
 * @param contentPadding (PaddingValues) padding values that are passed to all components
 * @param modifier (Modifier) modifier to change look
 */
@Composable
private fun RecipesDetail(
    selectedRecipe: SingleRecipeAllInfo?,
    onBackPressed: () -> Unit,
    contentPadding: PaddingValues,
    dbViewModel: DatabaseViewModel,
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
            Box (){
                Box() {
                    // If there is an image display it, otherwise display the image of our
                    // beautiful chef turner
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
                            modifier = Modifier.fillMaxWidth()
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
                        text = selectedRecipe?.recipe?.name ?: "",
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
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_small)),
                    ) {
                    IconButton(onClick = {
                        if(!selectedRecipe!!.recipe!!.favorite) {
                            dbViewModel.toggleFavoriteRecipe(selectedRecipe.recipe!!.rid, true)
                        }
                        else {
                            dbViewModel.toggleFavoriteRecipe(selectedRecipe.recipe!!.rid, false)
                        }
                    },
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.padding_detail_content_horizontal))
                    ) {
                        if(selectedRecipe?.recipe?.favorite == null) {
                            Icon(
                                imageVector = Icons.Filled.FavoriteBorder,
                                contentDescription = stringResource(R.string.fav_button_unfilled),
                                modifier = Modifier
                                    .size(dimensionResource(R.dimen.padding_detail_content_horizontal))
                            )
                        }
                        else if(!selectedRecipe.recipe.favorite) {
                            Icon(
                                imageVector = Icons.Filled.FavoriteBorder,
                                contentDescription = stringResource(R.string.fav_button_unfilled),
                                modifier = Modifier
                                    .size(dimensionResource(R.dimen.padding_detail_content_horizontal))
                            )
                        }
                        else {
                            Icon(
                                imageVector = Icons.Filled.Favorite,
                                tint = Color.Red,
                                contentDescription = stringResource(R.string.fav_button_filled),
                                modifier = Modifier
                                    .size(dimensionResource(R.dimen.padding_detail_content_horizontal))
                            )
                        }
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
    dbViewModel: DatabaseViewModel,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    displayFavorite: Boolean
) {
    Row(
        modifier = modifier
    ) {
        RecipesList(
            recipes = recipes,
            onClick = onClick,
            contentPadding = contentPadding, // Use contentPadding here
            displayFavorite = displayFavorite,
            modifier = Modifier
                .weight(2f)
                .padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )
        RecipesDetail(
            selectedRecipe = selectedRecipe,
            modifier = Modifier.weight(3f),
            contentPadding = contentPadding, // Use contentPadding here
            onBackPressed = onBackPressed,
            dbViewModel = dbViewModel

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