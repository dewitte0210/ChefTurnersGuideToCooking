package com.example.chefturnersguidetocooking

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
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
import androidx.navigation.NavController
import com.example.chefturnersguidetocooking.data.ExamplesDataProvider
import com.example.chefturnersguidetocooking.database.DatabaseViewModel
import com.example.chefturnersguidetocooking.model.Recipes
import com.example.chefturnersguidetocooking.ui.theme.RecipeTheme
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
    dbViewModel: DatabaseViewModel// Add NavController as a parameter
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
                recipes = uiState.RecipeList,
                selectedRecipes = uiState.currentRecipes,
                onClick = {
                    viewModel.updateCurrentRecipe(it)
                },
                onBackPressed = onBackPressed,
                contentPadding = innerPadding,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            if (uiState.isShowingListPage) {
                RecipesList(
                    recipes = uiState.RecipeList,
                    onClick = {
                        viewModel.updateCurrentRecipe(it)
                        viewModel.navigateToDetailPage()
                    },
                    modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium)),
                    contentPadding = innerPadding,
                )
            } else {
                RecipesDetail(
                    selectedRecipes = uiState.currentRecipes,
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

    object Favorites : BottomNavigationItem("favorites", { onClick ->
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
    recipes: Recipes,
    onItemClick: (Recipes) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(recipes) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            RecipeImageItem(
                recipes = recipes,
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
                    text = stringResource(recipes.titleResourceId),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.card_text_vertical_space))
                )
                Text(
                    text = stringResource(recipes.subtitleResourceId),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.secondary,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3
                )
                Row {
                }
            }
        }
    }
}


@Composable
private fun RecipeImageItem(recipes: Recipes, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(recipes.imageResourceId),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillWidth
        )
    }
}

@Composable
private fun RecipesList(
    recipes: List<Recipes>,
    onClick: (Recipes) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier.padding(top = dimensionResource(R.dimen.padding_medium)),
    ) {
        items(recipes, key = { Recipe -> Recipe.id }) { recipe ->
            RecipeListItem(
                recipes = recipe,
                onItemClick = onClick
            )
        }
    }
}

@Composable
private fun RecipesDetail(
    selectedRecipes: Recipes,
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
                    Image(
                        painter = painterResource(selectedRecipes.recipeImageBanner),
                        contentDescription = null,
                        alignment = Alignment.TopCenter,
                        contentScale = ContentScale.FillWidth,
                    )
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
                        text = stringResource(selectedRecipes.titleResourceId),
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
                text = stringResource(selectedRecipes.recipesDetails),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    vertical = dimensionResource(R.dimen.padding_detail_content_vertical),
                    horizontal = dimensionResource(R.dimen.padding_detail_content_horizontal)
                )
            )
        }
    }
}

@Composable
private fun RecipeListAndDetail(
    recipes: List<Recipes>,
    selectedRecipes: Recipes,
    onClick: (Recipes) -> Unit,
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
            selectedRecipes = selectedRecipes,
            modifier = Modifier.weight(3f),
            contentPadding = contentPadding, // Use contentPadding here
            onBackPressed = onBackPressed,
        )
    }
}

@Preview
@Composable
fun RecipesListItemPreview() {
    RecipeTheme {
        RecipeListItem(
            recipes = ExamplesDataProvider.defaultRecipe,
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
