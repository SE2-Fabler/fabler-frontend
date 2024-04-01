package com.se2.fabler.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.se2.fabler.AppModel
import com.se2.fabler.R
import com.se2.fabler.getTestAppModel
import com.se2.fabler.models.TabData
import com.se2.fabler.ui.components.CustomTabStrip
import com.se2.fabler.ui.components.Header
import com.se2.fabler.ui.views.BookShelfView

@Composable
fun HomePage(app: AppModel) {
    Box {
        Box(Modifier.padding(0.dp, 65.dp, 0.dp, 0.dp)) {
            CustomTabStrip(
                listOf(
                    TabData("NOVELS", R.drawable.baseline_menu_book_36) {
                        BookShelfView(app.loggedInUserData.stories)
                    },
                    TabData("BOOKMARKS", R.drawable.baseline_bookmark_48) {
                        BookShelfView(app.loggedInUserData.bookmarks)
                    },
                )
            )
        }
        Header({
            app.pushView("SearchPage")
        }, {
            app.userToDisplayId = app.loggedInUserData.user.id
            app.pushView("ProfilePage")
        })
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewHomePage() {
    Column { HomePage(getTestAppModel()) }
}
