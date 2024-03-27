package com.se2.fabler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.se2.fabler.ui.components.CardTab
import com.se2.fabler.ui.components.ToggleComponent
import com.se2.fabler.ui.header.Header
import com.se2.fabler.ui.header.SearchHeader
import com.se2.fabler.ui.tabs.bookmarks.BookmarkScreen
import com.se2.fabler.ui.tabs.creations.CreationScreen
import com.se2.fabler.ui.tabs.story.StoryScreen
import com.se2.fabler.ui.tabs.user.UserScreen
import com.se2.fabler.ui.theme.FablerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FablerTheme {
                Column {
                    var isSearch by remember { mutableStateOf(false) }
                    if (!isSearch) {
                        Header { isSearch = true }
                        ToggleComponent(
                            listOf(
                                CardTab("NOVELS") {
                                    CreationScreen()
                                },
                                CardTab("BOOKMARKS") {
                                    BookmarkScreen()
                                },
                            )
                        )
                        //ToggleComponent(text1 = "NOVELS", text2 = "BOOKMARKS")
                    } else {
                        SearchHeader { isSearch = false }
                        ToggleComponent(
                            listOf(
                                CardTab("Users") {
                                    UserScreen()
                                },
                                CardTab("Stories") {
                                    StoryScreen()
                                },
                            )
                        )
                    }
                }
            }
        }
    }
}
