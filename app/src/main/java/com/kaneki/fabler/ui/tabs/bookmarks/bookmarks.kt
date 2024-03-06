package com.kaneki.fabler.ui.tabs.bookmarks

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding


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
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BookmarkScreen() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.fillMaxSize()
    ) {
        items(creationList.size) { index ->
            CreationCard(creation = creationList[index])
        }
    }
}

@Composable
fun CreationCard(creation: Creation) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .aspectRatio(2f / 3f)
            .background(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                Image(
                    painter = painterResource(id = creation.imageResId),
                    contentDescription = "Creation Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .aspectRatio(2f / 3f),
                    contentScale = ContentScale.Crop // Scale the image to fill the available space
                )
            }
            Spacer(modifier = Modifier.height(16.dp)) // Add space between image and text
            Text(
                text = creation.title,
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black,
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCreationGrid() {
    MaterialTheme {
        BookmarkScreen()
    }
}