package com.se2.fabler.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.filled.Diversity1
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import com.se2.fabler.AppModel
import com.se2.fabler.getTestAppModel
import com.se2.fabler.models.TabData
import com.se2.fabler.ui.components.BackAndTextHeader
import com.se2.fabler.ui.components.CustomTabStrip
import com.se2.fabler.ui.views.UserListView

@Composable
fun FollowPage(app: AppModel) {
    var selectedTabIndex by remember { mutableIntStateOf(-1) }
    val userToDisplayId = app.currentViewData as Int

    Column {
        BackAndTextHeader("") { app.popView() }
        CustomTabStrip(
            listOf(
                TabData("FOLLOWING", Icons.AutoMirrored.Filled.MenuBook) {
                    UserListView(
                        app.repository.getFollowersResultStream(userToDisplayId, false)
                            .collectAsLazyPagingItems()
                    ) { user ->
                        app.pushView("ProfilePage", user.id)
                    }
                },
                TabData("FOLLOWERS", Icons.Default.Diversity1) {
                    UserListView(
                        app.repository.getFollowersResultStream(userToDisplayId, true)
                            .collectAsLazyPagingItems()
                    ) { user ->
                        app.pushView("ProfilePage", user.id)
                    }
                },
            )
        ) { tab: TabData, index: Int ->
            selectedTabIndex = index
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewFollowPage() {
    val app = getTestAppModel()
    app.pushView("FollowPage", 0)
    Column { FollowPage(app) }
}
