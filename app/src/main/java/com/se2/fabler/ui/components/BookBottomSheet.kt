package com.se2.fabler.ui.components

import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material.icons.outlined.BookmarkRemove
import androidx.compose.material.icons.outlined.DeleteForever
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.PersonSearch
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.se2.fabler.AppModel
import com.se2.fabler.TestDataSource
import com.se2.fabler.getTestAppModel
import com.se2.fabler.models.BookData
import com.se2.fabler.ui.theme.AppColors

class BookBottomSheetState {
    var popupVisible = mutableStateOf(false)
    var bottomSheetVisible = mutableStateOf(false)
    var currentBookData: BookData? = null
}

@Composable
private fun BookDetailsPopUp(state: BookBottomSheetState) {
    if (!state.popupVisible.value)
        return
    val contextMenuBook = state.currentBookData!!
    val scrollState = rememberScrollState()
    AlertDialog(
        onDismissRequest = { state.popupVisible.value = false },
        confirmButton = { /*TODO*/ },
        dismissButton = { /*TODO*/ },
        icon = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.Info,
                    contentDescription = "Info",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(4f.dp))
                Text(
                    text = "Novel Details"
                )
            }
        },
        title = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = contextMenuBook.title,
                    style = typography.titleLarge
                )
                Text(
                    text = "@${contextMenuBook.author}",
                    style = typography.titleMedium,
                    color = AppColors.PRIMARY_FONT_COLOR
                )
            }
        },
        text = {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(modifier = Modifier.height(200.dp)) {
                    DrawBook(book = contextMenuBook, onSelectBook = {}, {})
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = contextMenuBook.genre,
                    style = typography.titleMedium.copy(fontStyle = FontStyle.Italic),
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider()
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = contextMenuBook.description,
                    style = typography.bodyLarge,
                    modifier = Modifier.height(200.dp).verticalScroll(scrollState)
                )
            }
        }
    )
}

@Composable
private fun BottomSheetRow(text: String, icon: ImageVector, onClick: () -> Unit = {}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .clickable { onClick() }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = text,
            modifier = Modifier.size(32.dp),
            tint = Color.DarkGray
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = text,
            style = typography.titleLarge
        )
    }
}

@Composable
private fun BottomSheetOptions(
    app: AppModel,
    state: BookBottomSheetState,
    onSwitchProfile: (Int) -> Unit = {}
) {
    val contextMenuBook = state.currentBookData!!
    if (app.loggedInUserData.user.bookmarkList.contains(contextMenuBook.id)) {
        BottomSheetRow(
            "Remove Bookmark",
            Icons.Outlined.BookmarkRemove
        ) {
            // TODO: Add onClick action
        }
    } else {
        BottomSheetRow(
            "Bookmark",
            Icons.Outlined.BookmarkAdd
        ) {
            // TODO: Add onClick action
        }
    }
    BottomSheetRow(
        "Novel Details",
        Icons.Outlined.Book
    ) {
        state.bottomSheetVisible.value = false
        state.popupVisible.value = true
    }
    if (contextMenuBook.authorUserId != app.loggedInUserData.user.id) {
        BottomSheetRow(
            "View Profile",
            Icons.Outlined.PersonSearch
        ) {
            state.bottomSheetVisible.value = false
            onSwitchProfile(contextMenuBook.authorUserId)
            app.pushView(
                "ProfilePage",
                contextMenuBook.authorUserId
            )
        }
    } else {
        if (contextMenuBook.private) {
            BottomSheetRow(
                "Set to Public",
                Icons.Outlined.Visibility
            ) {
                // TODO:
            }
        } else {
            BottomSheetRow(
                "Set to Only You",
                Icons.Outlined.VisibilityOff
            ) {
                // TODO:
            }
        }
        BottomSheetRow(
            "Delete Permanently",
            Icons.Outlined.DeleteForever
        ) {
            // TODO:
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookBottomSheet(
    app: AppModel,
    state: BookBottomSheetState,
    onSwitchProfile: (Int) -> Unit = {}
) {
    val sheetState = rememberModalBottomSheetState()
    if (state.bottomSheetVisible.value) {
        ModalBottomSheet(
            onDismissRequest = { state.bottomSheetVisible.value = false },
            sheetState = sheetState
        ) {
            BottomSheetOptions(app, state, onSwitchProfile)
            Spacer(modifier = Modifier.height(28.dp))
        }
    }
    BookDetailsPopUp(state)
}

// View the book if the author of the book is not yourself
@Preview(showBackground = true)
@Composable
private fun PreviewBottomOptions1() {
    val state = BookBottomSheetState()
    state.currentBookData = TestDataSource().books[0]
    state.bottomSheetVisible.value = true
    MaterialTheme {
        Column {
            BottomSheetOptions(getTestAppModel(), state)
        }
    }
}

// Options if the author IS yourself and it is PRIVATE
@Preview(showBackground = true)
@Composable
private fun PreviewBottomOptions2() {
    val state = BookBottomSheetState()
    val app = getTestAppModel()
    state.currentBookData = TestDataSource().books[0].copy(
        authorUserId = app.loggedInUserData.user.id
    )
    state.bottomSheetVisible.value = true
    MaterialTheme {
        Column {
            BottomSheetOptions(app, state)
        }
    }
}

// Options if the author IS yourself and it is PUBLIC
@Preview(showBackground = true)
@Composable
private fun PreviewBottomOptions3() {
    val state = BookBottomSheetState()
    val app = getTestAppModel()
    state.currentBookData = TestDataSource().books[0].copy(
        authorUserId = app.loggedInUserData.user.id,
        private = false,
        id = 0
    )
    state.bottomSheetVisible.value = true
    MaterialTheme {
        Column {
            BottomSheetOptions(app, state)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBookPopUp() {
    val state = BookBottomSheetState()
    state.currentBookData = TestDataSource().books[0]
    state.bottomSheetVisible.value = true
    state.popupVisible.value = true
    MaterialTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            BookBottomSheet(getTestAppModel(), state)
        }
    }
}
