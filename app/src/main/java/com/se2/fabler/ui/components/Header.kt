package com.se2.fabler.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.se2.fabler.R

@Composable
fun Header(onSearchClick: () -> Unit, onUserClick: () -> Unit) {
    Surface(Modifier.fillMaxWidth().background(Color.White)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color.White)
                .padding(15.dp, 10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "Back",
                tint = Color.DarkGray,
                modifier = Modifier.size(36.dp)
                    .clickable(onClick = onUserClick)
            )
            Spacer(Modifier.weight(1f))
            Image(
                modifier = Modifier.size(200.dp, 60.dp),
                painter = painterResource(id = R.drawable.logo_header),
                contentDescription = "Header Logo: Fabler"
            )
            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.DarkGray,
                modifier = Modifier.size(36.dp)
                    .clickable(onClick = onSearchClick)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewHeader() {
    Header({}, {})
}
