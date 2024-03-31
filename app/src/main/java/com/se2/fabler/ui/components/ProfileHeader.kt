package com.se2.fabler.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.se2.fabler.R
import com.se2.fabler.models.UserData
import com.se2.fabler.ui.theme.AppColors
import com.se2.fabler.ui.theme.AppColors.Companion.SECONDARY_COLOR
import com.se2.fabler.ui.views.DrawUser


@Composable
fun ProfileHeader(user: UserData) {
    Surface(
        Modifier
            .fillMaxWidth()
            .background(SECONDARY_COLOR)
    ) {
        Column(
            modifier = Modifier.background(SECONDARY_COLOR)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_arrow),
                    contentDescription = "Back",
                    modifier = Modifier.padding(10.dp)
                    //.clickable(onClick = onSearchToggle)
                )
                Spacer(Modifier.weight(1f))
                Image(
                    painter = painterResource(id = R.drawable.baseline_search_36),
                    contentDescription = "Search",
                    modifier = Modifier.padding(0.dp, 0.dp, 20.dp, 5.dp)
                    //.clickable(onClick = onSearchToggle)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(15.dp, 10.dp, 15.dp, 10.dp)
            ) {
                DrawUser(user.profileImageResId, 80)
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Row (verticalAlignment = Alignment.CenterVertically) {
                        Column {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = user.name,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = AppColors.PRIMARY_FONT_COLOR,
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
                                    color = Color.DarkGray
                                )
                            }
                            if (user.isFollowing) {
                                Text(
                                    text = "Follows you",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
                                )
                            }
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
                    Spacer(modifier = Modifier.height(5.dp))
                    Row {
                        Text(
                            text = if (user.followerCount < 1000) { "${user.followerCount}" }
                            else if (user.followerCount < 1000000) { String.format("%.1fk", user.followerCount / 1000.0) }
                            else { String.format("%.1fM", user.followerCount / 1000000.0) },
                            style = MaterialTheme.typography.bodySmall,
                            color = AppColors.PRIMARY_FONT_COLOR,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                        Text(
                            text = " Followers",
                            style = MaterialTheme.typography.bodySmall,
                            color = AppColors.PRIMARY_FONT_COLOR
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = if (user.followingCount < 1000) { "${user.followingCount}" }
                            else if (user.followingCount < 1000000) { String.format("%.1fk", user.followingCount / 1000.0) }
                            else { String.format("%.1fM", user.followingCount / 1000000.0) },
                            style = MaterialTheme.typography.bodySmall,
                            color = AppColors.PRIMARY_FONT_COLOR,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                        Text(
                            text = " Following",
                            style = MaterialTheme.typography.bodySmall,
                            color = AppColors.PRIMARY_FONT_COLOR
                        )
                    }
                }
            }
            Row(
                    modifier = Modifier
                        .padding(15.dp, 0.dp, 15.dp, 0.dp)
                    ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = user.location,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.width(20.dp))
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Date Joined",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Joined ${user.joined}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .padding(15.dp, 0.dp, 15.dp, 15.dp)
            ) {
                Text(
                    text = user.about,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.DarkGray,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewProfileHeader() {
    ProfileHeader(
        UserData(
            id = 1,
            name = "Jaxen", "jaxendutta@gmail.com",
            username = "jaxdutta",
            profileImageResId = R.drawable.cat_1000,
            followerCount = 5870526,
            followingCount = 2334,
            stories = listOf(),
            bookmarks = listOf(),
            bookmarkPrivacy = true,
            about = "I am a writerhahahahaha" +
                    "ghahahajytefcgkugewkufygwelgflewrgflqeglfqejfjhqerwf;khqwebfkbqwekbf",
            location = "New York",
            joined = "2021-10-10",
            following = true,
            isFollowing = false
        )
    )
}