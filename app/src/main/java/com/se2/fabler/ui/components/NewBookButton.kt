package com.se2.fabler.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.se2.fabler.ui.theme.AppColors.Companion.PRIMARY_COLOR
import com.se2.fabler.ui.theme.AppColors.Companion.PRIMARY_FONT_COLOR

@Composable
fun NewBookButton() {
    val showDialog = remember { mutableStateOf(false) }
    val prompt = remember { mutableStateOf("") }
    val genre = remember { mutableStateOf("") }

    if (showDialog.value) {
        AlertDialog(
            onDismissRequest = { showDialog.value = false },
            title = { Text("Create New Book") },
            text = {
                Column(Modifier.navigationBarsWithImePadding()) {
                    OutlinedTextField(
                        value = prompt.value,
                        onValueChange = { prompt.value = it },
                        label = { Text("What do you want your novel to be about? Be creative with it.") },
                        singleLine = false,
                        modifier = Modifier.height(200.dp)
                    )
                    OutlinedTextField(
                        value = genre.value,
                        onValueChange = { genre.value = it },
                        label = { Text("Genre") }
                    )
                }

            },
            dismissButton = {
                Button(onClick = { showDialog.value = false }) {
                    Text("Cancel")
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        // TODO: Send data to the backend
                        // Something like: sendToBackend("GENRE: ${genre.value}, PROMPT: ${prompt.value}")
                        showDialog.value = false
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PRIMARY_FONT_COLOR,
                        contentColor = PRIMARY_COLOR
                    )
                ) {
                    Text("Create")
                }
            }
        )
    }

    IconButton(
        onClick = {
            // Write a function that would have a pop up with two text boxes and a send button at the end
            // The send button would send the data to the backend
            showDialog.value = true
        },
        modifier = Modifier.size(75.dp),
        colors = IconButtonColors(
            PRIMARY_FONT_COLOR,
            PRIMARY_COLOR,
            PRIMARY_COLOR,
            PRIMARY_FONT_COLOR
        ),
        content = {
            Icon(
                imageVector = Icons.Rounded.AddCircle,
                contentDescription = "Add",
                modifier = Modifier.size(100.dp)
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NewButtonPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp, 30.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        NewBookButton()
    }
}
