package com.kaneki.fabler.ui.tabs.creations

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.ui.Alignment


import com.kaneki.fabler.R

// Data class representing a creation
data class Creation(val id: Int, val title: String, val imageResId: Int)

// List of sample creations
val creationList = listOf(
    Creation(1, "Creation 1", R.drawable.bg1),
    Creation(2, "Creation 2", R.drawable.bg2),
    Creation(2, "Creation 3", R.drawable.bg3),
    Creation(2, "Creation 4", R.drawable.bg4),
    Creation(5, "Creation 5", R.drawable.bg5),
    Creation(6, "Creation 6", R.drawable.bg6),
    Creation(7, "Creation 7", R.drawable.bg7),
    Creation(8, "Creation 8", R.drawable.bg8),
    Creation(9, "Creation 9", R.drawable.bg9),
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CreationScreen() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize()
    ) {
        items(creationList.size) { index ->
            CreationCard(creation = creationList[index], isBookmarked = false, onBookmarkClicked = {})
        }
    }
}

@Composable
fun CreationCard(creation: Creation, isBookmarked: Boolean, onBookmarkClicked: (Boolean) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(2f / 3f) // Aspect ratio of the card (vertical rectangle)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color.Transparent, // Set surface color to transparent
            contentColor = Color.Black // Set content color for text
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color.Transparent) // Set background color of the Box to transparent
                ) {
                    Image(
                        painter = painterResource(id = creation.imageResId),
                        contentDescription = "Creation Image",
                        modifier = Modifier
                            .fillMaxSize()
                            .aspectRatio(2f/3f),
                        contentScale = ContentScale.Crop // Scale the image to fill the available space
                    )
                    IconButton(
                        onClick = { onBookmarkClicked(!isBookmarked) },
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        if (isBookmarked) {
                            Icon(Icons.Filled.Star, contentDescription = "Bookmarked")
                        } else {
                            Icon(Icons.Outlined.Star, contentDescription = "Bookmark")
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp)) // Add space between image and text
                Text(
                    text = creation.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreationGrid() {
    MaterialTheme {
        CreationScreen()
    }
}