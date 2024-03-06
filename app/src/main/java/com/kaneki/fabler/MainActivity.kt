package com.kaneki.fabler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaneki.fabler.ui.components.CardTab
import com.kaneki.fabler.ui.components.TabbedCard
import com.kaneki.fabler.ui.header.Header
import com.kaneki.fabler.ui.theme.FablerTheme

import com.kaneki.fabler.ui.tabs.user.UserScreen
import com.kaneki.fabler.ui.tabs.story.StoryScreen
import com.kaneki.fabler.ui.tabs.creations.CreationScreen
import com.kaneki.fabler.ui.tabs.bookmarks.BookmarkScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FablerTheme {

                Column {

                    Header()

                    TabbedCard(listOf(
                        CardTab("Creations") {
                            CreationScreen()
                        },
                        CardTab("Bookmarks") {
                            BookmarkScreen()
                        },
                    )
                    ) { _, _ ->
                    }
                }


            }
        }
    }
}

