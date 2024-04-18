package com.example.chefturnersguidetocooking

import android.graphics.drawable.shapes.Shape
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddRecipe(
    windowSize: WindowWidthSizeClass,
    onBackPressed: () -> Unit,
    ){
    var nameInput by remember { mutableStateOf("") }
    var originInput by remember { mutableStateOf("") }
    var descriptionInput by remember { mutableStateOf("") }
    var calorieInput by remember { mutableStateOf("") }
    var carbInput by remember { mutableStateOf("") }
    var fatInput by remember { mutableStateOf("") }
    var proteinInput by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    )
    {
        Text(
            text = stringResource(R.string.add_a_new_recipe),
            fontSize = 36.sp,
            modifier = Modifier
                .padding(16.dp)
        )
        AddRecipeInput(
            label = R.string.recipe_name,
            //leadingIcon = R.drawable.percent,
            value = nameInput,
            onValueChanged = { nameInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )
        Button(
            shape = RectangleShape,
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier
                .padding(bottom = 16.dp),
            onClick = { /*TODO*/ }
        ) {
            Image(
                painter = painterResource(R.drawable.chef),
                contentDescription = "Recipe Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        AddRecipeInput(
            label = R.string.recipe_origin,
            //leadingIcon = R.drawable.percent,
            value = originInput,
            onValueChanged = { originInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )
        AddRecipeInput(
            label = R.string.recipe_description,
            //leadingIcon = R.drawable.percent,
            value = descriptionInput,
            onValueChanged = { descriptionInput = it },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next),
            singleLine = false,
            modifier = Modifier
                .height(200.dp)
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        )
        RecipeInstructionsList(
            modifier = Modifier
        )
        Row(
            modifier = Modifier
                .padding(bottom = 16.dp)
        ){
            AddRecipeInput(
                label = R.string.calories,
                //leadingIcon = R.drawable.percent,
                value = calorieInput,
                onValueChanged = { calorieInput = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth(.5f)
            )
            AddRecipeInput(
                label = R.string.carbs,
                //leadingIcon = R.drawable.percent,
                value = carbInput,
                onValueChanged = { carbInput = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next),
                //horizontalAlignment = Alignment.CenterEnd,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Row(
            modifier = Modifier
                .padding(bottom = 16.dp)
        ){
            AddRecipeInput(
                label = R.string.fat,
                //leadingIcon = R.drawable.percent,
                value = fatInput,
                onValueChanged = { fatInput = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth(.5f)
            )
            AddRecipeInput(
                label = R.string.protein,
                //leadingIcon = R.drawable.percent,
                value = proteinInput,
                onValueChanged = { proteinInput = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Add Recipe"
            )
        }
    }
}

@Composable
fun RecipeInstructionsList(
    modifier: Modifier = Modifier,
){
    var steps = 1
    var instructionsInput by remember { mutableStateOf("") }

    var instructions = arrayOf("")

    if(instructions.size < steps){
        instructions + ""
    }

    for(i in 1..steps){

    }

    AddInstructionsStep(
        stepNum = steps,
        //leadingIcon = R.drawable.percent,
        value = instructionsInput,
        onValueChanged = { instructionsInput = it },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next),
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
    )
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Add a Step"
        )
    }
    
}

@Composable
fun InstructionStep(
    stepNum: Int,
    modifier: Modifier = Modifier
){
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddInstructionsStep(
    stepNum: Int,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier
)  {
    OutlinedTextField(
        value = value,
        colors = TextFieldDefaults.textFieldColors(focusedTextColor = Color.Black, unfocusedTextColor = Color.DarkGray),
        onValueChange = onValueChanged,
        singleLine = true,
        label = { Text("Step $stepNum") },
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

/**
 * Compostable for the Add Recipe Screen
 *
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeInput(
    @StringRes label: Int,
    //@DrawableRes leadingIcon: Int,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    singleLine: Boolean = true,
    modifier: Modifier = Modifier
)  {
    OutlinedTextField(
        value = value,
        colors = TextFieldDefaults.textFieldColors(focusedTextColor = Color.Black, unfocusedTextColor = Color.DarkGray),
        onValueChange = onValueChanged,
        singleLine = singleLine,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions,
        //leadingIcon = { Icon(painter = painterResource(id = leadingIcon), null) },
        modifier = modifier
        )
}

