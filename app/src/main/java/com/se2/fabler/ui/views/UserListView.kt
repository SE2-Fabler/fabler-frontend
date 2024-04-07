package com.se2.fabler.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.se2.fabler.TestDataSource
import com.se2.fabler.models.UserData
import com.se2.fabler.ui.components.SearchListButton
import com.se2.fabler.ui.theme.AppColors.Companion.PRIMARY_FONT_COLOR
import com.se2.fabler.ui.theme.AppColors.Companion.SCREEN_BACKGROUND_COLOR
import kotlinx.coroutines.flow.flowOf

@Composable
fun DrawUser(image: Int, size: Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(size.dp)
            .background(Color.Transparent)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(size.dp)
                .clip(RoundedCornerShape((size / 2 - 5).dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun UserListView(lazyUserData: LazyPagingItems<UserData>, onSelectUser: (UserData) -> Unit) {
    LazyVerticalGrid(
        GridCells.Fixed(1),
        modifier = Modifier
            .background(SCREEN_BACKGROUND_COLOR)
            .fillMaxHeight()
    ) {
        item {
            Spacer(modifier = Modifier.height(15.dp))
        }
        items(lazyUserData.itemCount) { idx ->
            val user = lazyUserData[idx] ?: return@items
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(15.dp, 10.dp, 15.dp, 10.dp)
                        .clickable { onSelectUser(user) },
                ) {
                    DrawUser(user.profileImageResId, 60)
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = user.name,
                                style = MaterialTheme.typography.titleSmall,
                                color = PRIMARY_FONT_COLOR,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = TextUnit(18.5f, TextUnitType.Sp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "@${user.username}", // TODO: ADD USER PROFILE LINK TO AUTHOR
                                style = MaterialTheme.typography.bodySmall,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.End,
                            )
                        }
                        if (user.isFollowing) {
                            Text(
                                text = "Follows you",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color.Gray
                            )
                        }
                        Text(
                            text = user.about,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.DarkGray,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    SearchListButton(
                        "Follow",
                        "Following",
                        Icons.Default.PersonAdd,
                        Icons.Default.PersonRemove,
                        user.imFollowing
                    )
                }
                HorizontalDivider()
            }

        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun UserListViewPreview() {
    val userData = listOf(
        TestDataSource().userdata,
        TestDataSource().userdata,
        TestDataSource().userdata,
        TestDataSource().userdata,
        TestDataSource().userdata
    )
    UserListView(flowOf(PagingData.from(userData)).collectAsLazyPagingItems()) { }
}
