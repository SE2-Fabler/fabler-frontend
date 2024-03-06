package com.kaneki.fabler.ui.tabs.story

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/*
@Composable
fun SimpleTabLayout() {
    val titles = listOf("Users", "Stories")
    var tabIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TabRow(selectedTabIndex = tabIndex) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index }
                    )
                }
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                //verticalArrangement = Arrangement.Top,
                //horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Selected page: ${titles[tabIndex]}")
                when (tabIndex) {
                    0 -> UserScreen()
                    1 -> StoryScreen()
                }
            }
        }

    )
}*/

class Story(val title: String, val author: String, val body: String){

}


@Composable
@Preview(showBackground = true)
fun StoryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        //verticalArrangement = Arrangement.Top,
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        StoryEntry(
            Story(
                title = "title 1", author = "test author",
                body = "this is the content of a story"
            )
        )
        StoryEntry(
            Story(
                title = "title 2", author = "test author",
                body = "this is the content of a story"
            )
        )
    }
}

@Composable
fun StoryPopup(content: Story) {
    Column(

    ){
        Text(
            text = content.title
        )
        Text(
            text = "By: ${content.author}"
        )
        Text(
            text = content.body
        )
    }
}

@Composable
fun StoryEntry(content: Story) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .border(BorderStroke(1.dp, Color.Black))
    ) {
        Text(
            text = content.title
        )
        Text(
            text = "By: ${content.author}"
        )
    }
}

