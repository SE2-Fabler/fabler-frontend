package com.se2.fabler.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.MenuBook
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.se2.fabler.AppModel
import com.se2.fabler.getTestAppModel
import com.se2.fabler.models.TabData
import com.se2.fabler.ui.components.CustomTabStrip
import com.se2.fabler.ui.components.Header
import com.se2.fabler.ui.components.NewBookButton
import com.se2.fabler.ui.views.BookShelfView

@Composable
fun HomePage(app: AppModel) {
    Box {
        Column {
            Header({
                app.pushView("SearchPage")
            }, {
                app.pushView("ProfilePage", app.loggedInUserData.user.id)
            })
            CustomTabStrip(
                listOf(
                    TabData("CREATIONS", Icons.AutoMirrored.Filled.MenuBook) {
                        BookShelfView(app.loggedInUserData.stories)
                    },
                    TabData("BOOKMARKS", Icons.Outlined.Bookmarks) {
                        BookShelfView(app.loggedInUserData.bookmarks)
                    },
                )
            )
        }
        Box (
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp, 30.dp),
            contentAlignment = androidx.compose.ui.Alignment.BottomEnd) {
            NewBookButton()
        }

    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewHomePage() {
    Column { HomePage(getTestAppModel()) }
}
