package com.se2.fabler.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.se2.fabler.AppModel
import com.se2.fabler.R
import com.se2.fabler.models.TabData
import com.se2.fabler.ui.components.CustomTabStrip
import com.se2.fabler.ui.components.SearchBar
import com.se2.fabler.ui.views.BookListView
import com.se2.fabler.ui.views.UserListView

@Composable
fun SearchPage(app: AppModel) {
    val searchQuery = remember { mutableStateOf("") }
    Box {
        Box(Modifier.padding(0.dp, 65.dp, 0.dp, 0.dp)) {
            CustomTabStrip(
                listOf(
                    TabData("NOVELS", R.drawable.baseline_menu_book_36) {
                        BookListView(
                            app.repository.getBookSearchResultStream(searchQuery.value)
                                .collectAsLazyPagingItems()
                        ) { book ->
                            // TODO: Implement book details navigation
                        }
                    },
                    TabData("READERS", R.drawable.baseline_diversity_1_36) {
                        UserListView(
                            app.repository.getUserSearchResultStream(searchQuery.value)
                                .collectAsLazyPagingItems()
                        ) { user ->
                            // TODO: Implement user profile navigation
                        }
                    },
                )
            )
        }
        SearchBar({
            searchQuery.value = ""
        }, {
            app.popView()
        })
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewSearchPage() {
    Column { SearchPage(AppModel()) }
}
