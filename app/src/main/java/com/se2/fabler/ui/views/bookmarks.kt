package com.se2.fabler.ui.views

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.se2.fabler.R
import com.se2.fabler.models.BookData
import com.se2.fabler.ui.components.BookShelf

private var sampleBookmarkData = listOf(
    BookData(1, "Title 1", R.drawable.bg1, "Author 1", "Genre 1", "Description 1", 4, true),
    BookData(2, "Title 2", R.drawable.bg2, "Author 2", "Genre 2", "Description 2", 4, true),
    BookData(3, "Title 3", R.drawable.bg3, "Author 3", "Genre 3", "Description 3", 4, true),
    BookData(4, "Title 4", R.drawable.bg4, "Author 4", "Genre 4", "Description 4", 4, true),
)

@Composable
fun BookmarkScreen() {
    BookShelf(sampleBookmarkData)
}

@Preview(showBackground = true)
@Composable
private fun PreviewBookmarkGrid() {
    MaterialTheme {
        BookmarkScreen()
    }
}