package com.se2.fabler.ui.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.se2.fabler.AppModel
import com.se2.fabler.TestDataSource
import com.se2.fabler.getTestAppModel
import com.se2.fabler.models.BookData
import com.se2.fabler.ui.components.BookBottomSheet
import com.se2.fabler.ui.components.BookBottomSheetState
import com.se2.fabler.ui.components.DrawBook
import com.se2.fabler.ui.theme.AppColors
import kotlinx.coroutines.flow.flowOf

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun DrawBookItem(
    book: BookData,
    onSelectBook: (BookData) -> Unit,
    onBookLongPress: (BookData) -> Unit
) {
    val haptics = LocalHapticFeedback.current
    ElevatedCard(
        modifier = Modifier
            .padding(10.dp, 10.dp)
            .combinedClickable(
                onClick = { onSelectBook(book) },
                onLongClick = {
                    haptics.performHapticFeedback(HapticFeedbackType.LongPress)
                    onBookLongPress(book)
                },
                onLongClickLabel = "Open book options"
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardColors(
            Color.White, Color.Black, Color.White, Color.White
        ),
        shape = RoundedCornerShape(20f)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .height(120.dp)
        ) {
            DrawBook(book = book, {}, {})
            Spacer(modifier = Modifier.width(10.dp))
            VerticalDivider()
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(
                    text = book.title,
                    style = typography.titleMedium,
                    color = AppColors.PRIMARY_FONT_COLOR,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "@${book.author}",
                    style = typography.bodySmall,
                    color = Color.DarkGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = book.genre,
                    style = typography.bodySmall.copy(fontStyle = FontStyle.Italic),
                    color = Color.Gray,
                )
                Spacer(modifier = Modifier.height(2.dp))
                Text(
                    text = book.description,
                    style = typography.bodySmall,
                    color = Color.DarkGray,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
            }
        } // End of Row
    } // End of ElevatedCard
}

@Composable
fun BookListView(
    lazyBookData: LazyPagingItems<BookData>, app: AppModel,
    onSwitchProfile: (Int) -> Unit = {}
) {
    val state by remember { mutableStateOf(BookBottomSheetState()) }

    Column(
        modifier = Modifier
            .background(AppColors.SCREEN_BACKGROUND_COLOR)
            .fillMaxHeight()
    ) {
        LazyVerticalGrid(
            GridCells.Fixed(1),
            modifier = Modifier
                .background(AppColors.SCREEN_BACKGROUND_COLOR)
                .fillMaxHeight()
        ) {
            item {
                Spacer(modifier = Modifier.height(15.dp))
            }
            items(lazyBookData.itemCount) { idx ->
                DrawBookItem(
                    lazyBookData[idx] ?: return@items,
                    {
                    // TODO: On top book
                    // app.pushView("BookPage")
                    }
                ) {
                    state.currentBookData = it
                    state.bottomSheetVisible.value = true
                }
            }
            item {
                Spacer(modifier = Modifier.height(25.dp))
            }
        }
        BookBottomSheet(app, state, onSwitchProfile)
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewBookListView() {
    val books = TestDataSource().books
    BookListView(flowOf(PagingData.from(books)).collectAsLazyPagingItems(), getTestAppModel())
}

@Composable
@Preview(showBackground = true)
private fun PreviewRow() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 20.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.Bookmark,
            contentDescription = "Bookmark",
            modifier = Modifier.size(36.dp),
            tint = Color.DarkGray
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = "Bookmark",
            style = typography.titleLarge
        )
    }
}
