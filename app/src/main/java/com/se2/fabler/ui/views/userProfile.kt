package com.se2.fabler.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.se2.fabler.R
import com.se2.fabler.models.TabData
import com.se2.fabler.models.UserData
import com.se2.fabler.ui.components.CustomTabStrip
import com.se2.fabler.ui.components.ProfileHeader

@Composable
fun UserProfile (user: UserData) {
    Box {
        Column {
            ProfileHeader(user = user)
            Box {
                CustomTabStrip(
                    listOf(
                        TabData("CREATIONS", R.drawable.baseline_menu_book_36) {
                            SearchBookShelf(user.stories)
                        },
                        TabData("BOOKMARKS", R.drawable.baseline_bookmark_48) {
                            SearchBookShelf(user.bookmarks)
                        },
                    )
                )
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
private fun PreviewUserProfile() {
    UserProfile(
        UserData(
            id = 1,
            name = "Jaxen",
            email = "jaxdutta@gmail.com",
            username = "jaxdutta",
            profileImageResId = R.drawable.cat_1000,
            followerCount = 284762,
            followingCount = 38762237,
            stories = listOf(),
            bookmarks = listOf(),
            bookmarkPrivacy = true,
            about = "I am a writer kufkfj\n" +
                    "jtfkg\n" +
                    "jtdf",
            location = "New York",
            joined = "2021-10-10",
            following = true,
            isFollowing = false
        )
    )
}
