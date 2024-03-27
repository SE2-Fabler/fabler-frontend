package com.se2.fabler.ui.views

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.se2.fabler.R
import com.se2.fabler.models.BookData
import com.se2.fabler.ui.components.BookShelf

private var sampleBookmarkData = listOf(
    BookData(1, "Title 1", R.drawable.bg1, true),
    BookData(2, "Title 2", R.drawable.bg2, true),
    BookData(3, "Title 3", R.drawable.bg3, true),
    BookData(4, "Title 4", R.drawable.bg4, true),
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