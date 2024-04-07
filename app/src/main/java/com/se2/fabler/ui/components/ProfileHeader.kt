package com.se2.fabler.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.se2.fabler.AppModel
import com.se2.fabler.TestDataSource
import com.se2.fabler.getTestAppModel
import com.se2.fabler.models.UserData
import com.se2.fabler.ui.theme.AppColors
import com.se2.fabler.ui.theme.AppColors.Companion.SECONDARY_COLOR
import com.se2.fabler.ui.views.DrawUser


@Composable
fun ProfileHeader(
    user: UserData,
    displaySettingIcon: Boolean,
    app: AppModel,
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Surface(
        Modifier
            .fillMaxWidth()
            .background(SECONDARY_COLOR)
    ) {
        Column(
            modifier = Modifier.background(SECONDARY_COLOR)
                .padding(15.dp, 0.dp, 15.dp, 0.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(0.dp, 10.dp, 0.dp, 10.dp)) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(36.dp)
                        .clickable(onClick = onBackClick)
                )
                if (displaySettingIcon) {
                    Spacer(Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        tint = Color.DarkGray,
                        modifier = Modifier.size(36.dp)
                            .clickable(onClick = onSettingsClick)
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(0.dp, 10.dp, 0.dp, 10.dp)
            ) {
                DrawUser(user.profileImageResId, 80)
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column(verticalArrangement = Arrangement.Center) {
                            Text(
                                text = user.name,
                                style = MaterialTheme.typography.titleLarge,
                                color = AppColors.PRIMARY_FONT_COLOR,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontSize = TextUnit(20f, TextUnitType.Sp),
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "@${user.username}", // TODO: ADD USER PROFILE LINK TO AUTHOR
                                style = MaterialTheme.typography.labelLarge,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.End,
                                color = Color.DarkGray
                            )
                        }
                        if (!displaySettingIcon) {
                            Spacer(modifier = Modifier.weight(1f))
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                SearchListButton(
                                    "Follow",
                                    "Following",
                                    Icons.Default.PersonAdd,
                                    Icons.Default.PersonRemove,
                                    user.imFollowing
                                )
                                if (user.isFollowing or true) {
                                    Text(
                                        text = "Follows you",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }
            }
            Row {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = user.location,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
                Spacer(modifier = Modifier.width(20.dp))
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Date Joined",
                    tint = Color.DarkGray,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = "Joined ${user.joined}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row (
                modifier = Modifier.clickable {
                    app.pushView("FollowPage", user.id)
                }
            ) {
                val followerCount = user.followers
                Text(
                    text = if (followerCount < 1000) {
                        "${followerCount}"
                    } else if (followerCount < 1000000) {
                        String.format("%.1fK", followerCount / 1000.0)
                    } else {
                        String.format("%.1fM", followerCount / 1000000.0)
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppColors.PRIMARY_FONT_COLOR,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = " Followers",
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppColors.PRIMARY_FONT_COLOR
                )
                Spacer(modifier = Modifier.width(10.dp))
                val followingCount = user.following
                Text(
                    text = if (followingCount < 1000) {
                        "${followingCount}"
                    } else if (followingCount < 1000000) {
                        String.format("%.1fK", followingCount / 1000.0)
                    } else {
                        String.format("%.1fM", followingCount / 1000000.0)
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppColors.PRIMARY_FONT_COLOR,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = " Following",
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppColors.PRIMARY_FONT_COLOR
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                Text(
                    text = user.about,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewProfileHeader() {
    ProfileHeader(TestDataSource().userdata, true, getTestAppModel(), {}, {})
}