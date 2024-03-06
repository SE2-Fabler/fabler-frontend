package com.kaneki.fabler.ui.tabs.story

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.kaneki.fabler.R
import com.kaneki.fabler.ui.tabs.user.UserEntry
import com.kaneki.fabler.ui.tabs.user.userList

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

class Story(val title: String, val author: String, val body: String, val profileImageResId: Int){}

val storyList = listOf(
    Story(
        title = "title 1", author = "test author",
        body = "this is the content of a story",
        R.drawable.fable1
    ),
    Story(
        title = "title 2", author = "test author",
        body = "this is the content of a story",
        R.drawable.fable2
    ),
    Story(
        title = "title 3", author = "test author",
        body = "this is the content of a story",
        R.drawable.fable2
    )
)

@Composable
@Preview(showBackground = true)
fun StoryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        //verticalArrangement = Arrangement.Top,
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        storyList.forEach { story ->
            Spacer(modifier = Modifier.height(8.dp))
            StoryEntry(story)
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
        }
    }
}

@Composable
fun StoryPopup(content: Story, showPopup: Boolean, onClickOutside: () -> Unit) {
    if (showPopup) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Gray)
                .zIndex(10F),
            contentAlignment = Alignment.Center
        ) {
            Popup(
                alignment = Alignment.Center,
                properties = PopupProperties(
                    excludeFromSystemGesture = true,
                ),
                onDismissRequest = { onClickOutside() }
            ) {
                Box(
                    Modifier
                        .width(200.dp)
                        .height(400.dp)
                        .background(Color.White)
                        .clip(RoundedCornerShape(4.dp))
                ) {
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
            }
        }
    }

}

@Composable
fun StoryEntry(content: Story) {
    var showPopup by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = content.profileImageResId),
            contentDescription = "Story image",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    showPopup = true
                }
        ) {
            Text(
                text = content.title
            )
            Text(
                text = "By: ${content.author}"
            )
        }
    }
    StoryPopup(content = content, showPopup = showPopup, onClickOutside = {showPopup = false})
}

