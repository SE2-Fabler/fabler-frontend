package com.se2.fabler.ui.tabs.bookmarks

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.se2.fabler.R
import com.se2.fabler.ui.tabs.Creation
import com.se2.fabler.ui.tabs.DrawBookCase

var bookmarkList = listOf(
    Creation(1, "Title 1", R.drawable.bg1, true),
    Creation(2, "Title 2", R.drawable.bg2, true),
    Creation(3, "Title 3", R.drawable.bg3, true),
    Creation(4, "Title 4", R.drawable.bg4, true),
)

@Composable
fun BookmarkScreen() {
    DrawBookCase(bookmarkList)
}

@Preview(showBackground = true)
@Composable
fun PreviewBookmarkGrid() {
    MaterialTheme {
        BookmarkScreen()
    }
}