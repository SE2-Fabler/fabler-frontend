package com.se2.fabler.ui.tabs.story

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.se2.fabler.R


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
            HorizontalDivider()
        }
    }
}

@Composable
fun StoryPopup(content: Story, showPopup: Boolean, onClose: () -> Unit) {
    AnimatedVisibility(
        visible = showPopup,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .zIndex(10f),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = content.title,
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "By: ${content.author}",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = content.body,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Close",
                        style = MaterialTheme.typography.titleSmall,
                        color = Color.Blue,
                        modifier = Modifier.clickable { onClose() }
                    )
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
    StoryPopup(content = content, showPopup = showPopup, {showPopup = false})
}

