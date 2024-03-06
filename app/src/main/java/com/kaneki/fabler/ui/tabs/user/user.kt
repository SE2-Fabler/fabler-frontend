package com.kaneki.fabler.ui.tabs.user

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Divider

import com.kaneki.fabler.R


data class User(val id: Int, val name: String, val email: String, val profileImageResId: Int)

val userList = listOf(
    User(1, "John Doe", "john@example.com", R.drawable.userimage1),
    User(2, "Dave Johnson", "djohn@example.com", R.drawable.userimage3),
    User(3, "Bob Johnson", "bob@example.com", R.drawable.userimage2),
    User(4, "Emily Brown", "emily@example.com", R.drawable.userimage4),
    User(5, "Michael Wilson", "michael@example.com", R.drawable.userimage5),
    User(6, "Sophia Martinez", "sophia@example.com", R.drawable.userimage6),
    User(7, "William Anderson", "william@example.com", R.drawable.userimage7),
    User(8, "Olivia Taylor", "olivia@example.com", R.drawable.userimage8),
)

@Preview(showBackground = true)
@Composable
fun UserScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        userList.forEach { user ->
            Spacer(modifier = Modifier.height(8.dp))
            UserEntry(user = user)
            Spacer(modifier = Modifier.height(8.dp))
            Divider()
        }
    }
}

@Composable
fun UserEntry(user: User) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = user.profileImageResId),
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = "Name: ${user.name}")
            Text(text = "Email: ${user.email}")
        }
    }
}
