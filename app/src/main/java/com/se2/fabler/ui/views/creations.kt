package com.se2.fabler.ui.views

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.se2.fabler.R
import com.se2.fabler.models.BookData
import com.se2.fabler.ui.components.BookShelf

private var sampleBookData = listOf(
    BookData(1, "Title 1", R.drawable.bg1, "Author 1", "Genre 1", "Description 1", true, 4, true),
    BookData(2, "Title 2", R.drawable.bg2, "Author 2", "Genre 2", "Description 2", false, 4, false),
    BookData(3, "Title 3", R.drawable.bg3, "Author 3", "Genre 3", "Description 3", true, 4, true),
    BookData(4, "Title 4", R.drawable.bg4, "Author 4", "Genre 4", "Description 4", false, 4, false),
    BookData(5, "Title 5", R.drawable.bg5, "Author 5", "Genre 5", "Description 5", true, 4, true),
    BookData(6, "Title 6", R.drawable.bg6, "Author 6", "Genre 6", "Description 6", false, 4, false),
    BookData(7, "Title 7", R.drawable.bg7, "Author 7", "Genre 7", "Description 7", true, 4, true),
    BookData(8, "Title 8", R.drawable.bg8, "Author 8", "Genre 8", "Description 8", false, 4, false),
    BookData(9, "Title 9", R.drawable.bg9, "Author 9", "Genre 9", "Description 9", true, 4, true),
    BookData(10, "Title 10", R.drawable.bg1, "Author 10", "Genre 10", "Description 10", false, 4, false),
    BookData(11, "Title 11", R.drawable.bg2, "Author 11", "Genre 11", "Description 11", true, 4, true),
    BookData(12, "Title 12", R.drawable.bg3, "Author 12", "Genre 12", "Description 12", false, 4, false),
    BookData(13, "Title 13", R.drawable.bg4, "Author 13", "Genre 13", "Description 13", true, 4, true),
    BookData(14, "Title 14", R.drawable.bg5, "Author 14", "Genre 14", "Description 14", false, 4, false),
    BookData(15, "Title 15", R.drawable.bg6, "Author 15", "Genre 15", "Description 15", true, 4, true),
    BookData(16, "Title 16", R.drawable.bg7, "Author 16", "Genre 16", "Description 16", false, 4, false),
    BookData(17, "Title 17", R.drawable.bg8, "Author 17", "Genre 17", "Description 17", true, 4, true),
    BookData(18, "Title 18", R.drawable.bg9, "Author 18", "Genre 18", "Description 18", false, 4, false),
    BookData(19, "Title 19", R.drawable.bg1, "Author 19", "Genre 19", "Description 19", true, 4, true),
    BookData(20, "Title 20", R.drawable.bg2, "Author 20", "Genre 20", "Description 20", false, 4, false),
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