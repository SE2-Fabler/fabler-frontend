package com.se2.fabler.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.se2.fabler.R
import com.se2.fabler.models.UserData
import com.se2.fabler.ui.components.SearchListButton
import com.se2.fabler.ui.theme.AppColors.Companion.PRIMARY_FONT_COLOR
import com.se2.fabler.ui.theme.AppColors.Companion.SCREEN_BACKGROUND_COLOR

val sampleSearchUserData = listOf(
    UserData(
        1,
        "Eliza",
        "eli@hi.com",
        "d3mig0dd3ss",
        R.drawable.cat_1000,
        34,
        4,
        listOf(),
        listOf(),
        true,
        "Hello",
        "Nowhere",
        "Jan 10, 2020",
        following = true,
        isFollowing = true
    ),
    UserData(
        2,
        "John",
        "jo@qw.com",
        "johndoeYAKNOW",
        R.drawable.cat_1001,
        3,
        0,
        listOf(),
        listOf(),
        false,
        "I'm a piano",
        "Norfolk, UK",
        "Feb 23, 2019",
        following = true,
        isFollowing = true
    ),
    UserData(
        3,
        "Jane",
        "ja@skdfc.co",
        "jane",
        R.drawable.cat_1002,
        3452,
        345,
        listOf(),
        listOf(),
        true,
        "Don't talk to me! :/",
        "WHEREVER IDK",
        "Mar 7, 2021",
        following = true,
        isFollowing = false
    ),
    UserData(
        4,
        "Doe",
        "domi@ijd.kv",
        "doe",
        R.drawable.cat_1003,
        453,
        3495,
        listOf(),
        listOf(),
        false,
        "Pretty princess haha",
        "YesHere",
        "Oct 10, 2022",
        following = false,
        isFollowing = false
    ),
    UserData(
        5,
        "Jaxen",
        "jax@en.ca",
        "taxthejax",
        R.drawable.cat_1004,
        3765,
        2376,
        listOf(),
        listOf(),
        true,
        "one of the best ikwim",
        "Waterloo, Canada",
        "May 23, 2001",
        following = true,
        isFollowing = true
    ),
    UserData(
        6,
        "Maxy",
        "maxxxine@uw.ca",
        "maxy",
        R.drawable.cat_1005,
        2455,
        657,
        listOf(),
        listOf(),
        false,
        "I'm a cat",
        "Kitchener, Canada",
        "May 5, 2003",
        true,
        isFollowing = false
    ),
    UserData(
        1,
        "Eliza",
        "eli@hi.com",
        "d3mig0dd3ss",
        R.drawable.cat_1000,
        34,
        4,
        listOf(),
        listOf(),
        true,
        "Hello",
        "Nowhere",
        "Jan 10, 2020",
        following = true,
        isFollowing = true
    ),
    UserData(
        2,
        "John",
        "jo@qw.com",
        "johndoeYAKNOW",
        R.drawable.cat_1001,
        3,
        0,
        listOf(),
        listOf(),
        false,
        "I'm a piano",
        "Norfolk, UK",
        "Feb 23, 2019",
        following = true,
        isFollowing = true
    ),
    UserData(
        3,
        "Jane",
        "ja@skdfc.co",
        "jane",
        R.drawable.cat_1002,
        3452,
        345,
        listOf(),
        listOf(),
        true,
        "Don't talk to me! :/",
        "WHEREVER IDK",
        "Mar 7, 2021",
        following = true,
        isFollowing = false
    ),
    UserData(
        4,
        "Doe",
        "domi@ijd.kv",
        "doe",
        R.drawable.cat_1003,
        453,
        3495,
        listOf(),
        listOf(),
        false,
        "Pretty princess haha",
        "YesHere",
        "Oct 10, 2022",
        following = false,
        isFollowing = false
    ),
    UserData(
        5,
        "Jaxen",
        "jax@en.ca",
        "taxthejax",
        R.drawable.cat_1004,
        3765,
        2376,
        listOf(),
        listOf(),
        true,
        "one of the best ikwim",
        "Waterloo, Canada",
        "May 23, 2001",
        following = true,
        isFollowing = true
    ),
    UserData(
        6,
        "Maxy",
        "maxxxine@uw.ca",
        "maxy",
        R.drawable.cat_1005,
        2455,
        657,
        listOf(),
        listOf(),
        false,
        "I'm a cat",
        "Kitchener, Canada",
        "May 5, 2003",
        true,
        isFollowing = false
    ),UserData(
        1,
        "Eliza",
        "eli@hi.com",
        "d3mig0dd3ss",
        R.drawable.cat_1000,
        34,
        4,
        listOf(),
        listOf(),
        true,
        "Hello",
        "Nowhere",
        "Jan 10, 2020",
        following = true,
        isFollowing = true
    ),
    UserData(
        2,
        "John",
        "jo@qw.com",
        "johndoeYAKNOW",
        R.drawable.cat_1001,
        3,
        0,
        listOf(),
        listOf(),
        false,
        "I'm a piano",
        "Norfolk, UK",
        "Feb 23, 2019",
        following = true,
        isFollowing = true
    ),
    UserData(
        3,
        "Jane",
        "ja@skdfc.co",
        "jane",
        R.drawable.cat_1002,
        3452,
        345,
        listOf(),
        listOf(),
        true,
        "Don't talk to me! :/",
        "WHEREVER IDK",
        "Mar 7, 2021",
        following = true,
        isFollowing = false
    ),
    UserData(
        4,
        "Doe",
        "domi@ijd.kv",
        "doe",
        R.drawable.cat_1003,
        453,
        3495,
        listOf(),
        listOf(),
        false,
        "Pretty princess haha",
        "YesHere",
        "Oct 10, 2022",
        following = false,
        isFollowing = false
    ),
    UserData(
        5,
        "Jaxen",
        "jax@en.ca",
        "taxthejax",
        R.drawable.cat_1004,
        3765,
        2376,
        listOf(),
        listOf(),
        true,
        "one of the best ikwim",
        "Waterloo, Canada",
        "May 23, 2001",
        following = true,
        isFollowing = true
    ),
    UserData(
        6,
        "Maxy",
        "maxxxine@uw.ca",
        "maxy",
        R.drawable.cat_1005,
        2455,
        657,
        listOf(),
        listOf(),
        false,
        "I'm a cat",
        "Kitchener, Canada",
        "May 5, 2003",
        true,
        isFollowing = false
    ),
)

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
fun SearchUserList(sampleUserList: List<UserData>) {
    var selectedUser by remember { mutableStateOf<UserData?>(null) }
    LazyVerticalGrid(
        GridCells.Fixed(1),
        modifier = Modifier
            .background(SCREEN_BACKGROUND_COLOR)
    ) {
        item {
            Spacer(modifier = Modifier.height(15.dp))
        }
        items(sampleUserList.size) { index ->
            val user = sampleUserList[index]
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(15.dp, 10.dp, 15.dp, 10.dp)
                        .clickable { selectedUser = user },
                ) {
                    DrawUser(user.profileImageResId, 60)
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Row (
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
                        R.drawable.baseline_person_add_36,
                        R.drawable.baseline_person_remove_36,
                        user.following
                    )
                }
                HorizontalDivider()
            }

        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
    selectedUser?.let { UserProfile(user = it) }
}

@Composable
@Preview(showBackground = true)
fun SearchUser() {
    SearchUserList(sampleSearchUserData)
}