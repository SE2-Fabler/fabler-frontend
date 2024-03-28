package com.se2.fabler.ui.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.se2.fabler.R
import com.se2.fabler.models.BookData
import com.se2.fabler.ui.components.DrawBook
import com.se2.fabler.ui.components.SHELF_WOOD_HEIGHT
import com.se2.fabler.ui.components.SHELF_WOOD_PADDING
import com.se2.fabler.ui.components.drawScaledBitmap
import com.se2.fabler.ui.theme.AppColors.Companion.SCREEN_BACKGROUND_COLOR
import com.se2.fabler.ui.theme.AppColors.Companion.PRIMARY_FONT_COLOR
import com.se2.fabler.ui.theme.AppColors.Companion.PRIMARY_COLOR

private var sampleSearchBookData = listOf(
    BookData(
        1, "Fortune Killer", R.drawable.bg1, "demigod3ss", "Horror",
        "Jaxen’s got a secret past that threatens to bring down the school’s whole social order – and much more.",
        4, true
    ),
    BookData(2, "Title 2", R.drawable.bg2, "Author 2", "Genre 2", "Description 2", 4, true),
    BookData(3, "Title 3", R.drawable.bg3, "Author 3", "Genre 3", "Description 3", 4, false),
    BookData(4, "Title 4", R.drawable.bg4, "Author 4", "Genre 4", "Description 4", 4, true),
    BookData(5, "Title 5", R.drawable.bg5, "Author 5", "Genre 5", "Description 5", 4, false),
    BookData(6, "Title 6", R.drawable.bg6, "Author 6", "Genre 6", "Description 6", 4, true),
    BookData(7, "Title 7", R.drawable.bg7, "Author 7", "Genre 7", "Description 7", 4, false),
    BookData(8, "Title 8", R.drawable.bg8, "Author 8", "Genre 8", "Description 8", 4, true),
    BookData(9, "Title 9", R.drawable.bg9, "Author 9", "Genre 9", "Description 9", 4, false),
    BookData(10, "Title 10", R.drawable.bg1, "Author 10", "Genre 10", "Description 10", 4, true),
    BookData(11, "Title 11", R.drawable.bg2, "Author 11", "Genre 11", "Description 11", 4, false),
    BookData(12, "Title 12", R.drawable.bg3, "Author 12", "Genre 12", "Description 12", 4, true),
    BookData(13, "Title 13", R.drawable.bg4, "Author 13", "Genre 13", "Description 13", 4, false),
    BookData(14, "Title 14", R.drawable.bg5, "Author 14", "Genre 14", "Description 14", 4, true),
    BookData(15, "Title 15", R.drawable.bg6, "Author 15", "Genre 15", "Description 15", 4, false),
)

@Composable
private fun DrawSearchShelf() {
    Column(Modifier.padding(0.dp, 100.dp, 0.dp, 20.dp)) {
        val shelfLeftImg = ImageBitmap.imageResource(R.drawable.shelf_left)
        val shelfRightImg = ImageBitmap.imageResource(R.drawable.shelf_right)
        val shelfMiddleImg = ImageBitmap.imageResource(R.drawable.shelf_middle)
        ElevatedCard(
            modifier = Modifier
                .height(SHELF_WOOD_HEIGHT * 2 / 3)
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
                    Size(shelfLeftImg.width.toFloat() * 2 / 3, SHELF_WOOD_HEIGHT.toPx() * 2 / 3)
                )
                // Shelf middle
                drawScaledBitmap(
                    this, shelfMiddleImg,
                    Offset(shelfLeftImg.width.toFloat() * 2 / 3, shelfLoc.y),
                    Size(shelfSize.width - 2 * 2 / 3 * shelfLeftImg.width, shelfSize.height * 2 / 3)
                )
                // Shelf right end
                drawScaledBitmap(
                    this, shelfRightImg,
                    Offset(shelfSize.width - shelfRightImg.width - 1, 0f),
                    Size(shelfRightImg.width.toFloat() * 2 / 3, SHELF_WOOD_HEIGHT.toPx() * 2 / 3)
                )
            }
        }
    }
}

@Composable
private fun SearchListButton(name: String, activatedName: String, icon: Int, activatedIcon: Int, active: Boolean) {
    val activated = remember { mutableStateOf(active) }

    val buttonColor = if (activated.value) PRIMARY_FONT_COLOR else PRIMARY_COLOR
    val contentColor = if (activated.value) Color.White else PRIMARY_FONT_COLOR
    val buttonText = if (activated.value) activatedName else name
    val buttonIcon = if (activated.value) activatedIcon else icon

    Button(
        onClick = { activated.value = !activated.value },
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = contentColor,
            disabledContainerColor = Color.Gray,
            disabledContentColor = Color.White
        ),
        shape = RoundedCornerShape(14.dp),
        elevation = ButtonDefaults.buttonElevation(2.dp),
        contentPadding = PaddingValues(6.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(buttonIcon),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp)) // Add space between icon and text
            Text(
                text = buttonText,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Composable
fun SearchBookShelf(bookSplit: List<BookData>) {
    LazyVerticalGrid(
        GridCells.Fixed(1),
        modifier = Modifier
            .background(SCREEN_BACKGROUND_COLOR)
    ) {
        item {
            Spacer(modifier = Modifier.height(25.dp))
        }
        items(bookSplit.size) { index ->
            val book = bookSplit[index]
            Row(
                verticalAlignment = Alignment.Bottom,
            ) {
                Row(
                    modifier = Modifier
                        .height(100.dp)
                        .padding(15.dp, 0.dp, 0.dp, 0.dp),
                ) {
                    DrawBook(book)
                    Spacer(modifier = Modifier.width(10.dp))
                    Row {
                        Column(
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {
                            Row(
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.Start,
                                modifier = Modifier
                                    .height(70.dp)
                                    .padding(0.dp, 0.dp, 0.dp, 10.dp)
                            ) {
                                Column {
                                    Text(
                                        text = book.title,
                                        style = MaterialTheme.typography.titleSmall,
                                        color = PRIMARY_FONT_COLOR,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = "@${book.author}", // TODO: ADD USER PROFILE LINK TO AUTHOR
                                        style = MaterialTheme.typography.bodySmall,
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Text(
                                        text = book.genre,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = Color.Gray
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                VerticalDivider()
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = book.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    overflow = TextOverflow.Ellipsis,
                                    fontSize = TextUnit(10f, TextUnitType.Sp),
                                    letterSpacing = TextUnit(0.1f, TextUnitType.Sp),
                                )
                            }
                            Spacer(modifier = Modifier.height(0.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                SearchListButton(
                                    "Read",
                                    "Read",
                                    icon = R.drawable.baseline_menu_book_36,
                                    R.drawable.baseline_menu_book_36,
                                    false
                                )
                                SearchListButton(
                                    "Bookmark",
                                    "Bookmarked",
                                    R.drawable.baseline_bookmark_border_48,
                                    R.drawable.baseline_bookmark_48,
                                    book.bookmarked
                                )
                                SearchListButton(
                                    "Share",
                                    "Share",
                                    R.drawable.baseline_send_36,
                                    R.drawable.baseline_send_36,
                                    false
                                )
                            }
                        }
                    }
                }
            }
            DrawSearchShelf()
        }
        item {
            Spacer(modifier = Modifier.height(25.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun StoryScreen() {
    SearchBookShelf(sampleSearchBookData)
}
