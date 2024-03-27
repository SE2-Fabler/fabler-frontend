package com.se2.fabler.ui.tabs.creations

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.se2.fabler.R
import com.se2.fabler.ui.tabs.Creation
import com.se2.fabler.ui.tabs.DrawBookCase

var creationList = listOf(
    Creation(1, "Creation 1", R.drawable.bg1, true),
    Creation(2, "Creation 2", R.drawable.bg2, false),
    Creation(3, "Creation 3", R.drawable.bg3, true),
    Creation(4, "Creation 4", R.drawable.bg4, false),
    Creation(5, "Creation 5", R.drawable.bg5, true),
    Creation(6, "Creation 6", R.drawable.bg6, false),
    Creation(7, "Creation 7", R.drawable.bg7, true),
    Creation(8, "Creation 8", R.drawable.bg8, false),
    Creation(9, "Creation 9", R.drawable.bg9, true),
    Creation(10, "Creation 10", R.drawable.bg1,false),
    Creation(11, "Creation 11", R.drawable.bg2, true),
    Creation(12, "Creation 12", R.drawable.bg3, false),
    Creation(13, "Creation 13", R.drawable.bg4, true),
    Creation(14, "Creation 14", R.drawable.bg5, false),
    Creation(15, "Creation 15", R.drawable.bg6, true),
    Creation(16, "Creation 16", R.drawable.bg7, false),
    Creation(17, "Creation 17", R.drawable.bg8, true),
    Creation(18, "Creation 18", R.drawable.bg9, false),
    Creation(19, "Creation 19", R.drawable.bg1, true),
    Creation(20, "Creation 20", R.drawable.bg2, false),
)

@Composable
fun CreationScreen() {
    DrawBookCase(creationList)
}

@Preview(showBackground = true)
@Composable
fun PreviewCreationGrid() {
    MaterialTheme {
        CreationScreen()
    }
}