package com.kaneki.fabler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.kaneki.fabler.ui.components.CardTab
import com.kaneki.fabler.ui.components.TabbedCard
import com.kaneki.fabler.ui.header.Header
import com.kaneki.fabler.ui.header.SearchHeader
import com.kaneki.fabler.ui.theme.FablerTheme

import com.kaneki.fabler.ui.tabs.creations.CreationScreen
import com.kaneki.fabler.ui.tabs.bookmarks.BookmarkScreen
import com.kaneki.fabler.ui.tabs.story.StoryScreen
import com.kaneki.fabler.ui.tabs.user.UserScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FablerTheme {

                Column {

                    var isSearch by remember { mutableStateOf(false) }

                    if(!isSearch) {

                        Header { isSearch = true }

                        TabbedCard(listOf(
                            CardTab("Creations") {
                                CreationScreen()
                            },
                            CardTab("Bookmarks") {
                                BookmarkScreen()
                            },
                        ))

                    } else {

                        SearchHeader { isSearch = false }

                        TabbedCard(listOf(
                            CardTab("Users") {
                                UserScreen()
                            },
                            CardTab("Stories") {
                                StoryScreen()
                            },
                        ))

                    }
                }


            }
        }
    }
}

