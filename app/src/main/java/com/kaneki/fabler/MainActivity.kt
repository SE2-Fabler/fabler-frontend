package com.kaneki.fabler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.tooling.preview.Preview
import com.kaneki.fabler.ui.theme.FablerTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FablerTheme {
                // A surface container using the 'background' color from the theme
                MainContent()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
            text = "Hello $name!",
            modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FablerTheme {
        Greeting("Android")
    }
}

@Composable
fun MainContent() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Column() {
            Greeting("Android")
            SimpleTabLayout()
        }
    }
}

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
}

@Composable
fun UserScreen() {
    UserEntry(name = "test author")
    UserEntry(name = "test author 2")
}

@Composable
fun StoryScreen() {
    StoryEntry(title = "title 1", author = "test author")
    StoryEntry(title = "title 2", author = "test author")
}

@Composable
fun StoryEntry(title: String, author: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .border(BorderStroke(1.dp, Color.White))
    ) {
        Text(
            text = title
        )
        Text(
            text = "By: $author"
        )
    }
}

@Composable
fun UserEntry(name: String) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .border(BorderStroke(1.dp, Color.White))
    ) {
        Text(
            text = name
        )
    }
}