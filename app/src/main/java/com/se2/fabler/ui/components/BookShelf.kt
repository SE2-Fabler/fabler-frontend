package com.se2.fabler.ui.components

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
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.se2.fabler.R
import com.se2.fabler.models.BookData

// Static configuration variables
val SHELF_HEIGHT = 200f.dp
val SHELF_WOOD_HEIGHT = 25f.dp
val SHELF_WOOD_PADDING = 10f.dp

private var sampleBookData = listOf(
    BookData(1, "Title 1", R.drawable.bg1, "Author 1", "Genre 1", "Description 1", 4, true),
    BookData(2, "Title 2", R.drawable.bg2, "Author 2", "Genre 2", "Description 2", 4, true),
    BookData(3, "Title 3", R.drawable.bg3, "Author 3", "Genre 3", "Description 3", 4, true),
    BookData(4, "Title 4", R.drawable.bg4, "Author 4", "Genre 4", "Description 4", 4, true),
)

@Preview(showBackground = true)
@Composable
private fun PreviewBookShelfComponent() {
    MaterialTheme {
        BookShelf(sampleBookData)
    }
}

fun drawScaledBitmap(
    canvas: DrawScope,
    bitmap: ImageBitmap,
    topLeft: Offset,
    dstSize: Size
) {
    val scaledBitmap = Bitmap.createScaledBitmap(
        bitmap.asAndroidBitmap(),
        (dstSize.width).toInt(),
        (dstSize.height).toInt(),
        false
    )
    canvas.drawImage(
        scaledBitmap.asImageBitmap(),
        topLeft = topLeft
    )
}

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
                ),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardColors(
                Color.Transparent, Color.Transparent, Color.Transparent, Color.Transparent
            ),
            shape = RoundedCornerShape(10f)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val shelfLoc = Offset.Zero
                val shelfSize = Size(size.width, SHELF_WOOD_HEIGHT.toPx())
                // Shelf left end
                drawScaledBitmap(
                    this, shelfLeftImg,
                    Offset.Zero,
                    Size(shelfLeftImg.width.toFloat(), SHELF_WOOD_HEIGHT.toPx())
                )
                // Shelf middle
                drawScaledBitmap(
                    this, shelfMiddleImg,
                    Offset(shelfLeftImg.width.toFloat(), shelfLoc.y),
                    Size(shelfSize.width - 2 * shelfLeftImg.width, shelfSize.height)
                )
                // Shelf right end
                drawScaledBitmap(
                    this, shelfRightImg,
                    Offset(shelfSize.width - shelfRightImg.width - 1, 0f),
                    Size(shelfRightImg.width.toFloat(), SHELF_WOOD_HEIGHT.toPx())
                )
            }
        }
    }
}

@Composable
fun DrawBook(creation: BookData) {
    val creationCover = ImageBitmap.imageResource(creation.imageResId)
    val bookCover = ImageBitmap.imageResource(R.drawable.book_case)
    ElevatedCard(
        modifier = Modifier
            .aspectRatio(2f / 3f)
            .background(Color.Transparent),
        shape = RoundedCornerShape(3.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
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
                            alpha = 0.4f
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BookShelf(creationList: List<BookData>) {
    val bookSplit = creationList.chunked(3)
    LazyVerticalGrid(
        GridCells.Fixed(1),
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xfffff8f3))
            .padding(0.dp)
    ) {
        item {
            Box(modifier = Modifier.height(10.dp)) { }
        }
        items(bookSplit.size) { index ->
            val chunk = bookSplit[index]
            Row(
                modifier = Modifier
                    .height(SHELF_HEIGHT),
                verticalAlignment = Alignment.Bottom,
            ) {
                Row(
                    modifier = Modifier
                        .height(165.dp)
                        .padding(0.dp, 0.dp, 0.dp, SHELF_WOOD_HEIGHT + 1.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    chunk.forEach { creation -> DrawBook(creation) }
                }
            }
            DrawHorizontalShelf()
        }
        item {
            Box(modifier = Modifier.height(50.dp)) { }
        }
    }
}
