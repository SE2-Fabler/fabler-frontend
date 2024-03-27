package com.se2.fabler.ui.tabs

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.se2.fabler.R

// Static configuration variables
val SHELF_HEIGHT = 200f.dp
val SHELF_WOOD_HEIGHT = 25f.dp
val SHELF_WOOD_PADDING = 10f.dp

data class Creation(val id: Int, val title: String, val imageResId: Int, val bookmarked: Boolean)

@Composable
fun DrawHorizontalShelf() {
    Column(Modifier.padding(0.dp, SHELF_HEIGHT - SHELF_WOOD_HEIGHT, 0.dp, 0.dp)) {
        val shelfLeftImg = ImageBitmap.imageResource(R.drawable.shelf_left)
        val shelfRightImg = ImageBitmap.imageResource(R.drawable.shelf_right)
        val shelfMiddleImg = ImageBitmap.imageResource(R.drawable.shelf_middle)
        ElevatedCard(
            modifier = Modifier
                .height(SHELF_WOOD_HEIGHT)
                .fillMaxWidth()
                .padding(
                    SHELF_WOOD_PADDING, 0.dp
                ), elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ), colors = CardColors(
                Color.Transparent, Color.Transparent, Color.Transparent, Color.Transparent
            ), shape = RoundedCornerShape(10f)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val shelfLoc = Offset.Zero
                val shelfSize = Size(size.width, SHELF_WOOD_HEIGHT.toPx())

                // Shelf left end
                Bitmap.createScaledBitmap(
                    shelfLeftImg.asAndroidBitmap(),
                    shelfLeftImg.width,
                    SHELF_WOOD_HEIGHT.toPx().toInt(),
                    false
                ).let {
                    drawImage(
                        it.asImageBitmap(), topLeft = Offset.Zero
                    )
                }

                // Shelf middle
                Bitmap.createScaledBitmap(
                    shelfMiddleImg.asAndroidBitmap(),
                    shelfSize.width.toInt() - 2 * shelfLeftImg.width,
                    shelfSize.height.toInt(),
                    false
                ).let {
                    drawImage(
                        it.asImageBitmap(), topLeft = Offset(
                            shelfLeftImg.width.toFloat(), shelfLoc.y
                        )
                    )
                }

                // Shelf right end
                Bitmap.createScaledBitmap(
                    shelfRightImg.asAndroidBitmap(),
                    shelfRightImg.width,
                    SHELF_WOOD_HEIGHT.toPx().toInt(),
                    false
                ).let {
                    drawImage(
                        it.asImageBitmap(), topLeft = Offset(
                            shelfSize.width - shelfRightImg.width - 1, 0f
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun DrawBook(creation: Creation, index: Int) {
    val creationCover = ImageBitmap.imageResource(creation.imageResId)
    val bookCover = ImageBitmap.imageResource(R.drawable.book_case)
    ElevatedCard(
        modifier = Modifier
            .aspectRatio(2f / 3f)
            .background(Color.Transparent),
        shape = RoundedCornerShape(3.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
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
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    val creationHeightScale = size.height / creationCover.height
                    Bitmap.createScaledBitmap(
                        creationCover.asAndroidBitmap(),
                        (creationCover.width * creationHeightScale).toInt(),
                        (creationCover.height * creationHeightScale).toInt(),
                        false
                    ).let {
                        drawImage(
                            it.asImageBitmap(),
                            topLeft = Offset(0.0f, 0.0f),
                        )
                    }

                    val bookHeightScale = size.height / bookCover.height
                    Bitmap.createScaledBitmap(
                        bookCover.asAndroidBitmap(),
                        (bookCover.width * bookHeightScale).toInt(),
                        (bookCover.height * bookHeightScale).toInt(),
                        false
                    ).let {
                        drawImage(
                            it.asImageBitmap(),
                            topLeft = Offset(0.0f, 0.0f),
                            blendMode = BlendMode.Hardlight,
                            alpha = 0.2f
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun DrawBookCase(creationList: List<Creation>) {
    val bookSplit = creationList.chunked(3)
    LazyVerticalGrid(GridCells.Fixed(1), modifier = Modifier.fillMaxSize()) {
        items(bookSplit.size) { index ->
            val chunk = bookSplit[index]
            Row(
                modifier = Modifier
                    .height(SHELF_HEIGHT),
                verticalAlignment = Alignment.Bottom,
            ) {
                Row(
                    modifier = Modifier
                        .height(180.dp)
                        .padding(0.dp, 0.dp, 0.dp, SHELF_WOOD_HEIGHT+4.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    chunk.forEachIndexed { i, creation ->
                        DrawBook(creation, i)
                    }
                }
            }
            DrawHorizontalShelf()
        }
        item {
            Box(modifier = Modifier.height(50.dp)) { }
        }
    }
}
