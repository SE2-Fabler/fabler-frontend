package com.se2.fabler.ui.components

import android.graphics.Bitmap
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.se2.fabler.AppModel
import com.se2.fabler.R
import com.se2.fabler.TestDataSource
import com.se2.fabler.getTestAppModel
import com.se2.fabler.models.BookData

// Static configuration variables
val SHELF_HEIGHT = 200f.dp
val SHELF_WOOD_HEIGHT = 25f.dp
val SHELF_WOOD_PADDING = 10f.dp


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
fun DrawHorizontalShelf(
    scaleFactor: Float = 1.0f,
    topPadding: Dp = SHELF_HEIGHT - SHELF_WOOD_HEIGHT
) {
    Column(Modifier.padding(0.dp, topPadding, 0.dp, 0.dp)) {
        val shelfLeftImg = ImageBitmap.imageResource(R.drawable.shelf_left)
        val shelfRightImg = ImageBitmap.imageResource(R.drawable.shelf_right)
        val shelfMiddleImg = ImageBitmap.imageResource(R.drawable.shelf_middle)
        ElevatedCard(
            modifier = Modifier
                .height(SHELF_WOOD_HEIGHT * scaleFactor)
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
                val shelfSize = Size(size.width, SHELF_WOOD_HEIGHT.toPx() * scaleFactor)
                // Shelf left end
                drawScaledBitmap(
                    this, shelfLeftImg,
                    Offset.Zero,
                    Size(shelfLeftImg.width.toFloat(), shelfSize.height)
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
                    Size(shelfRightImg.width.toFloat(), shelfSize.height)
                )
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DrawBook(
    book: BookData,
    onSelectBook: (BookData) -> Unit,
    onBookLongPress: (BookData) -> Unit
) {
    val creationCover = ImageBitmap.imageResource(book.imageResId)
    val bookCover = ImageBitmap.imageResource(R.drawable.book_case)
    val haptics = LocalHapticFeedback.current
    ElevatedCard(
        modifier = Modifier
            .aspectRatio(2f / 3f)
            .background(Color.Transparent)
            .combinedClickable(
                onClick = { onSelectBook(book) },
                onLongClick = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    onBookLongPress(book)
                },
                onLongClickLabel = "Open book options"
            ),
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
                    drawRect(Color.White, blendMode = BlendMode.Clear)
                    val creationHeightScale = size.height / creationCover.height
                    with(drawContext.canvas.nativeCanvas) {
                        val checkPoint = saveLayer(null, null)
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
                        restoreToCount(checkPoint)
                    } // End native canvas
                } // End Canvas
            } // End Box
        } // End Column
    } // End ElevatedCard
}

@Composable
fun BookShelf(
    creationList: List<BookData>,
    app: AppModel,
    onSwitchProfile: (Int) -> Unit = {}
) {
    val state by remember { mutableStateOf(BookBottomSheetState()) }
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
        items(bookSplit.size) {
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
                    bookSplit[it].forEach { creation ->
                        DrawBook(
                            creation,
                            {}) {
                            state.currentBookData = it
                            state.bottomSheetVisible.value = true
                        }
                    }
                    BookBottomSheet(app, state, onSwitchProfile)
                }
            }
            DrawHorizontalShelf()
        }
        item {
            Box(modifier = Modifier.height(50.dp)) { }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBookShelfComponent() {
    MaterialTheme {
        BookShelf(TestDataSource().books, getTestAppModel())
    }
}
