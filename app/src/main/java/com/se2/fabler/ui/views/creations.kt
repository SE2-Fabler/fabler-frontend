package com.se2.fabler.ui.views

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.se2.fabler.R
import com.se2.fabler.models.BookData
import com.se2.fabler.ui.components.BookShelf

private var sampleBookData = listOf(
    BookData(1, "Creation 1", R.drawable.bg1, true),
    BookData(2, "Creation 2", R.drawable.bg2, false),
    BookData(3, "Creation 3", R.drawable.bg3, true),
    BookData(4, "Creation 4", R.drawable.bg4, false),
    BookData(5, "Creation 5", R.drawable.bg5, true),
    BookData(6, "Creation 6", R.drawable.bg6, false),
    BookData(7, "Creation 7", R.drawable.bg7, true),
    BookData(8, "Creation 8", R.drawable.bg8, false),
    BookData(9, "Creation 9", R.drawable.bg9, true),
    BookData(10, "Creation 10", R.drawable.bg1,false),
    BookData(11, "Creation 11", R.drawable.bg2, true),
    BookData(12, "Creation 12", R.drawable.bg3, false),
    BookData(13, "Creation 13", R.drawable.bg4, true),
    BookData(14, "Creation 14", R.drawable.bg5, false),
    BookData(15, "Creation 15", R.drawable.bg6, true),
    BookData(16, "Creation 16", R.drawable.bg7, false),
    BookData(17, "Creation 17", R.drawable.bg8, true),
    BookData(18, "Creation 18", R.drawable.bg9, false),
    BookData(19, "Creation 19", R.drawable.bg1, true),
    BookData(20, "Creation 20", R.drawable.bg2, false),
)

@Composable
fun CreationScreen() {
    BookShelf(sampleBookData)
}

@Preview(showBackground = true)
@Composable
private fun PreviewCreationGrid() {
    MaterialTheme {
        CreationScreen()
    }
}