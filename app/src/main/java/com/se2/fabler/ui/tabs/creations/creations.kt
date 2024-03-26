package com.se2.fabler.ui.tabs.creations

import android.graphics.Bitmap.createScaledBitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.se2.fabler.R

// Static configuration variables
val SHELF_HEIGHT = 200f.dp
val SHELF_WOOD_HEIGHT = 25f.dp
val SHELF_WOOD_PADDING = 10f.dp
val BOOK_HEIGHT = 150.dp
val BOOK_WIDTH = 100.dp

data class Creation(val id: Int, val title: String, val imageResId: Int)
var creationList = listOf(
    Creation(1, "Creation 1", R.drawable.bg1),
    Creation(2, "Creation 2", R.drawable.bg2),
    Creation(3, "Creation 3", R.drawable.bg3),
    Creation(4, "Creation 4", R.drawable.bg4),
    Creation(5, "Creation 5", R.drawable.bg5),
    Creation(6, "Creation 6", R.drawable.bg6),
    Creation(7, "Creation 7", R.drawable.bg7),
    Creation(8, "Creation 8", R.drawable.bg8),
    Creation(9, "Creation 9", R.drawable.bg9),
    Creation(10, "Creation 10", R.drawable.bg1),
    Creation(11, "Creation 11", R.drawable.bg2),
    Creation(12, "Creation 12", R.drawable.bg3),
    Creation(13, "Creation 13", R.drawable.bg4),
    Creation(14, "Creation 14", R.drawable.bg5),
    Creation(15, "Creation 15", R.drawable.bg6),
    Creation(16, "Creation 16", R.drawable.bg7),
    Creation(17, "Creation 17", R.drawable.bg8),
    Creation(18, "Creation 18", R.drawable.bg9),
    Creation(19, "Creation 19", R.drawable.bg1),
    Creation(20, "Creation 20", R.drawable.bg2),
)

val shelfGradient = object : ShaderBrush() {
    override fun createShader(size: Size): Shader {
        val biggerDimension = maxOf(size.height, size.width)
        return RadialGradientShader(
            colors = listOf(Color(0xfffaffff), Color(0xfffff8f2)),
            center = size.center,
            radius = biggerDimension / 2f,
            colorStops = listOf(0f, 0.95f)
        )
    }
}

@Composable
fun CreationShelf() {
    val shelfLeftImg = ImageBitmap.imageResource(R.drawable.shelf_left)
    val shelfRightImg = ImageBitmap.imageResource(R.drawable.shelf_right)
    val shelfMiddleImg = ImageBitmap.imageResource(R.drawable.shelf_middle)
    Box(modifier = Modifier.height(SHELF_HEIGHT)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val shelfLoc =
                Offset(SHELF_WOOD_PADDING.toPx(), (SHELF_HEIGHT - SHELF_WOOD_HEIGHT).toPx())
            val shelfSize =
                Size(size.width - 2 * SHELF_WOOD_PADDING.toPx(), SHELF_WOOD_HEIGHT.toPx())

            // Draw shelf gradient
            drawRect(
                brush = shelfGradient,
                topLeft = Offset.Zero,
                size = size
            )

            // Shelf left end
            createScaledBitmap(
                shelfLeftImg.asAndroidBitmap(),
                shelfLeftImg.width,
                SHELF_WOOD_HEIGHT.toPx().toInt(),
                false
            ).let {
                drawImage(
                    it.asImageBitmap(), topLeft = Offset(
                        SHELF_WOOD_PADDING.toPx() + 1, (SHELF_HEIGHT - SHELF_WOOD_HEIGHT).toPx()
                    )
                )
            }

            // Shelf middle
            createScaledBitmap(
                shelfMiddleImg.asAndroidBitmap(),
                shelfSize.width.toInt() - 2 * shelfLeftImg.width,
                shelfSize.height.toInt(),
                false
            ).let {
                drawImage(
                    it.asImageBitmap(), topLeft = Offset(
                        shelfLeftImg.width + SHELF_WOOD_PADDING.toPx(), shelfLoc.y
                    )
                )
            }

            // Shelf right end
            createScaledBitmap(
                shelfRightImg.asAndroidBitmap(),
                shelfRightImg.width,
                SHELF_WOOD_HEIGHT.toPx().toInt(),
                false
            ).let {
                drawImage(
                    it.asImageBitmap(), topLeft = Offset(
                        SHELF_WOOD_PADDING.toPx() + shelfSize.width.toInt() - shelfRightImg.width - 1,
                        (SHELF_HEIGHT - SHELF_WOOD_HEIGHT).toPx()
                    )
                )
            }

        }
    }
}

@Composable
fun CreationCard(creation: Creation, index: Int) {
    val creationCover = ImageBitmap.imageResource(creation.imageResId)
    Box(modifier = Modifier.height(200.dp)) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            createScaledBitmap(
                creationCover.asAndroidBitmap(),
                BOOK_WIDTH.toPx().toInt(),
                BOOK_HEIGHT.toPx().toInt(),
                false
            ).let {
                drawImage(
                    it.asImageBitmap(),
                    topLeft = Offset(
                        (size.width - 3 * BOOK_WIDTH.toPx())/4 + index * (BOOK_WIDTH.toPx() + (size.width - 3 * BOOK_WIDTH.toPx())/4),
                        //BOOK_PADDING.toPx() + index * (BOOK_WIDTH.toPx() + BOOK_PADDING.toPx()),
                        (SHELF_HEIGHT - SHELF_WOOD_HEIGHT).toPx() - BOOK_HEIGHT.toPx()
                    )
                )
            }
        }
    }
}

@Composable
fun CreationScreen() {
    val bookSplit = creationList.chunked(3)
    LazyVerticalGrid(GridCells.Fixed(1), modifier = Modifier.fillMaxSize()) {
        items(bookSplit.size) { index ->
            val chunk = bookSplit[index]
            CreationShelf()
            chunk.forEachIndexed { index, creation ->
                CreationCard(creation, index)
            }
        }
        item {
            Box(modifier = Modifier.height(50.dp)) { }
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