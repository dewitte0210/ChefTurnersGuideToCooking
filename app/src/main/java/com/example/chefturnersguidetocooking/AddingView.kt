package com.example.chefturnersguidetocooking

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDismissState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.chefturnersguidetocooking.model.Instruction
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingView() {

    var nameInput by remember { mutableStateOf("") }
    var originInput by remember { mutableStateOf("") }
    var descriptionInput by remember { mutableStateOf("") }
    var dishTypeInput by remember { mutableStateOf("") }
    var prepTimeInput by remember { mutableStateOf("") }
    var servingsInput by remember { mutableStateOf("") }
    var cookTimeInput by remember { mutableStateOf("") }
    var calorieInput by remember { mutableStateOf("") }
    var carbInput by remember { mutableStateOf("") }
    var fatInput by remember { mutableStateOf("") }
    var proteinInput by remember { mutableStateOf("") }
    val ingredientList = remember { mutableStateListOf<String>() }
    val instructionList = remember { mutableStateListOf<Instruction>() }


    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.add_a_new_recipe),
                    fontWeight = FontWeight.Bold
                )
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            ),
        )
        Column(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.padding_large))
                .verticalScroll(rememberScrollState())
        ) {
            AddRecipeInput(
                label = R.string.recipe_name,
                value = nameInput,
                onValueChanged = { nameInput = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .padding(bottom = dimensionResource(R.dimen.padding_medium))
                    .fillMaxWidth()
            )
            Button(
                shape = RectangleShape,
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .padding(bottom = dimensionResource(R.dimen.padding_medium)),
                /**
                 * This is where we would add the ability
                 * to add an image for the recipe, but for now
                 * it's just the chef
                 */
                /**
                 * This is where we would add the ability
                 * to add an image for the recipe, but for now
                 * it's just the chef
                 */
                onClick = {
                    /*TODO*/
                }) {
                Image(
                    painter = painterResource(R.drawable.temp_recipe_image),
                    contentDescription = "Recipe Image",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            AddRecipeInput(
                label = R.string.recipe_origin,
                value = originInput,
                onValueChanged = { originInput = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .padding(bottom = dimensionResource(R.dimen.padding_medium))
                    .fillMaxWidth()
            )
            AddRecipeInput(
                label = R.string.dish_type,
                value = dishTypeInput,
                onValueChanged = { dishTypeInput = it },
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
                value = descriptionInput,
                onValueChanged = { descriptionInput = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                singleLine = false,
                modifier = Modifier
                    // .height(200.dp)
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            )
            val sheetState = rememberModalBottomSheetState()
            val scope = rememberCoroutineScope()
            var showBottomSheet by remember { mutableStateOf(false) }
            var ingredientList = ArrayList<String>()
            ingredientList.add("Butter")
            ingredientList.add("Milk")
            ingredientList.add("Eggs")
            ingredientList.add("Flour")
            Button(
                /**
                 * Button that goes to the adding ingredients view
                 */
                /**
                 * Button that goes to the adding ingredients view
                 */
                onClick = {
                    showBottomSheet = true
                }, modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Manage Ingredients"
                )
            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {
                    // Sheet content
                    //List all ingredients from database
                    Box(modifier = Modifier.background(Color.LightGray)) {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxHeight()
                        ) {
                            items(items = ingredientList, itemContent = { item ->
                                Log.d("COMPOSE", "This get rendered $item")
                                when (item) {
                                    item -> {
                                        Box(
                                            modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = 16.dp)
                                            .clickable {
                                                Log.d("Hobby", "Pressed Box")
                                            }) {
                                            Text(text = item, style = TextStyle(fontSize = 80.sp))
                                        }
                                    }

                                    else -> {
                                        Text(text = item, style = TextStyle(fontSize = 80.sp))
                                    }
                                }
                            })
                        }
                    }
                    Button(onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }) {
                        Text("Add Ingredients")
                    }
                }
            }
            RecipeInstructions(
                instructionList = instructionList,
                modifier = Modifier
            )
            AddRecipeInput(
                label = R.string.prep_time,
                value = prepTimeInput,
                onValueChanged = { prepTimeInput = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            )
            AddRecipeInput(
                label = R.string.cook_time,
                value = cookTimeInput,
                onValueChanged = { cookTimeInput = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            )
            AddRecipeInput(
                label = R.string.servings,
                value = servingsInput,
                onValueChanged = { servingsInput = it },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .fillMaxWidth()
            )
            Text(
                text = "Nutrition Facts (per serving)",
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )
            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp)
            ) {
                AddRecipeInput(
                    label = R.string.calories,
                    value = calorieInput,
                    onValueChanged = { calorieInput = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                )
                AddRecipeInput(
                    label = R.string.carbs,
                    value = carbInput,
                    onValueChanged = { carbInput = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Row(
                modifier = Modifier
                    .padding(bottom = 16.dp)
            ) {
                AddRecipeInput(
                    label = R.string.fat,
                    value = fatInput,
                    onValueChanged = { fatInput = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                )
                AddRecipeInput(
                    label = R.string.protein,
                    value = proteinInput,
                    onValueChanged = { proteinInput = it },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
            Button(
                /**
                 * The on click function will add all of the data to the database.
                 */
                onClick = {
                    /*TODO*/
                }, modifier = Modifier
                    .padding(bottom = 52.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Add Recipe"
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeInstructions(
    instructionList: SnapshotStateList<Instruction>,
    modifier: Modifier = Modifier
) {
    var steps by remember { mutableIntStateOf(1) }
    var instructionsInput by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    if (steps <= instructionList.size) {
        steps = instructionList.size + 1
    }

    Text(
        text = stringResource(R.string.recipe_instructions),
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        modifier = modifier
    )

    var cardLines = 1

    instructionList.forEach {
        cardLines += (it.instruction.length / 25) + 1
    }

    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier
            .height((28 * cardLines).dp)
    ) {
        items(instructionList, key = { Instruction -> Instruction.stepNum }) { instruction ->
            var instructionText by remember { mutableStateOf(instruction.instruction) }
            var alertDialogOpen by remember { mutableStateOf(false) }

            fun deleteInstruction() {
                instructionList.remove(instruction)
                val stepNum = instruction.stepNum
                for (step in instructionList) {
                    if (step.stepNum > stepNum) {
                        step.stepNum--
                    }
                }
                steps--
            }

            fun editInstruction(newInstruction: String) {
                instructionList[instruction.stepNum - 1].instruction = newInstruction
                instructionText = newInstruction
            }

            val state = rememberDismissState(confirmValueChange = {
                if (it == DismissValue.DismissedToStart) {
                    deleteInstruction()
                } else if (it == DismissValue.DismissedToEnd) {
                    alertDialogOpen = true
                }
                false
            })
            SwipeToDismiss(state = state, background = {
                val color = when (state.dismissDirection) {
                    DismissDirection.EndToStart -> Color.Red
                    DismissDirection.StartToEnd -> Color.Green
                    null -> Color.Transparent
                }
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = modifier
                        .padding(bottom = 8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete",
                            modifier = Modifier.align(Alignment.CenterEnd)
                        )
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit",
                            modifier = Modifier.align(Alignment.CenterStart)
                        )
                    }
                }
            }, dismissContent = {
                InstructionStep(
                    instruction = instruction,
                    instructionText = instructionText,
                    onValueChanged = { instructionText = it },
                    deleteInstruction = { deleteInstruction() },
                    editInstruction = { editInstruction(instructionText) },
                    alertDialogOpen = alertDialogOpen,
                    onAlertDialogOpenChanged = { alertDialogOpen = it },
                    modifier = modifier
                )
            })
        }
    }

    AddInstructionsStep(
        stepNum = steps,
        value = instructionsInput,
        onValueChanged = { instructionsInput = it },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        focusRequester = focusRequester,
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
    )
    Button(
        onClick = {
            if (instructionsInput != "") {
                instructionList.add(Instruction(steps++, instructionsInput))
                instructionsInput = ""
            }
            focusRequester.requestFocus()
        }, modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Add a Step"
        )
    }
}


@Composable
fun EditInstructionDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogStep: Int,
    dialogInput: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = {
        onDismissRequest()
    }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "Edit Step $dialogStep:",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = modifier
            )
            AddInstructionsStep(
                stepNum = dialogStep,
                value = dialogInput,
                onValueChanged = onValueChanged,
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )
            Row(
                modifier = modifier.align(Alignment.End)
            ) {
                Button(
                    onClick = { onConfirmation() },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text(
                        text = "Confirm"
                    )
                }
                Button(
                    onClick = { onDismissRequest() },
                    modifier = Modifier
                ) {
                    Text(
                        text = "Cancel"
                    )
                }
            }
        }
    }
}


@Composable
fun InstructionStep(
    instruction: Instruction,
    instructionText: String,
    onValueChanged: (String) -> Unit,
    deleteInstruction: () -> Unit,
    editInstruction: (String) -> Unit,
    alertDialogOpen: Boolean,
    onAlertDialogOpenChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Edit",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { onAlertDialogOpenChanged(true) })
            Text(
                text = "${instruction.stepNum}: ",
                fontSize = 18.sp,
                modifier = Modifier.padding(start = 4.dp)
            )
            Text(
                text = instructionText,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(.9f)
            )
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .clickable { deleteInstruction() })
            if (alertDialogOpen) {
                EditInstructionDialog(
                    onDismissRequest = {
                        onValueChanged(instruction.instruction)
                        onAlertDialogOpenChanged(false)
                    },
                    onConfirmation = {
                        editInstruction(instructionText)
                        onAlertDialogOpenChanged(false)
                    },
                    onValueChanged = onValueChanged,
                    dialogStep = instruction.stepNum,
                    dialogInput = instructionText,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun AddInstructionsStep(
    stepNum: Int,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = FocusRequester()
) {
    OutlinedTextField(
        value = value,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.DarkGray
        ),
        textStyle = TextStyle.Default.copy(
            fontSize = 18.sp
        ),
        onValueChange = onValueChanged,
        singleLine = true,
        label = { Text("Step $stepNum") },
        keyboardOptions = keyboardOptions,
        modifier = modifier.focusRequester(focusRequester)
    )
}

/**
 * Compostable for the Add Recipe Screen
 *
 */
@Composable
fun AddRecipeInput(
    @StringRes label: Int,
    value: String,
    onValueChanged: (String) -> Unit,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
) {
    OutlinedTextField(
        value = value,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.DarkGray
        ),
        textStyle = TextStyle.Default.copy(
            fontSize = 18.sp
        ),
        onValueChange = onValueChanged,
        singleLine = singleLine,
        label = { Text(stringResource(label)) },
        keyboardOptions = keyboardOptions,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    AddingView()
}