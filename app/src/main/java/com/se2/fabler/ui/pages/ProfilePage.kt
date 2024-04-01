package com.se2.fabler.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.se2.fabler.AppModel
import com.se2.fabler.R
import com.se2.fabler.models.TabData
import com.se2.fabler.ui.components.CustomTabStrip
import com.se2.fabler.ui.components.ProfileHeader
import com.se2.fabler.ui.views.BookListView
import kotlinx.coroutines.flow.flowOf

@Composable
fun ProfilePage(app: AppModel) {
    val user = app.getUserProfile(0)
    val stories = app.getUserCreations(0)
    val bookmarks = app.getUserBookmarks(0)
    val lazyStories = flowOf(PagingData.from(stories)).collectAsLazyPagingItems()
    val lazyBookmarks = flowOf(PagingData.from(bookmarks)).collectAsLazyPagingItems()
    Box {
        Column {
            ProfileHeader(user)
            Box {
                CustomTabStrip(
                    listOf(
                        TabData("CREATIONS", R.drawable.baseline_menu_book_36) {
                            BookListView(lazyStories) {}
                        },
                        TabData("BOOKMARKS", R.drawable.baseline_bookmark_48) {
                            BookListView(lazyBookmarks) {}
                        },
                    )
                )
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
private fun PreviewProfilePage() {
    ProfilePage(AppModel())
}
