package com.se2.fabler.ui.views

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.se2.fabler.AppModel
import com.se2.fabler.models.BookData
import com.se2.fabler.ui.components.BookShelf

@Composable
fun BookShelfView(data: List<BookData>) {
    BookShelf(data)
}

@Preview(showBackground = true)
@Composable
private fun PreviewBookShelfView() {
    MaterialTheme {
        BookShelfView(AppModel().getUserCreations(0))
    }
}
