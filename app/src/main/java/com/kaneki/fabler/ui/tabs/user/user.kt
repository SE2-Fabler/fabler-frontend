package com.kaneki.fabler.ui.tabs.user

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun UserScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        //verticalArrangement = Arrangement.Top,
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserEntry(name = "test author")
        UserEntry(name = "test author 2")
    }
}

@Composable
fun UserEntry(name: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Black))
    ) {
        Text(
            text = name
        )
    }
}