package com.se2.fabler.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.se2.fabler.AppModel
import com.se2.fabler.getTestAppModel
import com.se2.fabler.models.TabData
import com.se2.fabler.ui.components.CustomTabStrip
import com.se2.fabler.ui.components.ProfileHeader
import com.se2.fabler.ui.views.BookListView
import kotlinx.coroutines.flow.flowOf

@Composable
fun ProfilePage(app: AppModel) {
    val isCurrentUser = app.userToDisplayId == app.loggedInUserData.user.id
    val allUserData = if (isCurrentUser) {
        app.loggedInUserData
    } else {
        app.service.getUserDataAll(app.userToDisplayId)
    }
    val lazyStories = flowOf(PagingData.from(allUserData.stories)).collectAsLazyPagingItems()
    val lazyBookmarks = flowOf(PagingData.from(allUserData.bookmarks)).collectAsLazyPagingItems()
    Box {
        Column {
            ProfileHeader(allUserData.user, isCurrentUser, {
                app.popView()
            }, {
                // TODO: Implement settings page
            })
            Box {
                CustomTabStrip(
                    listOf(
                        TabData("CREATIONS", Icons.AutoMirrored.Filled.MenuBook) {
                            BookListView(lazyStories) {}
                        },
                        TabData("BOOKMARKS", Icons.Outlined.Bookmarks) {
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
    ProfilePage(getTestAppModel())
}
