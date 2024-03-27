package com.se2.fabler.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
fun Header(onSearchToggle: () -> Unit) {
    Surface(Modifier.fillMaxWidth()) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(Color.White)
                .padding(0.dp, 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_search_36),
                contentDescription = "Search",
                modifier = Modifier
                    .padding(10.dp)
                    .clickable(onClick = onSearchToggle)
            )
            Spacer(Modifier.weight(1f))
            Image(
                modifier = Modifier.size(200.dp, 50.dp),
                painter = painterResource(id = R.drawable.logo_header),
                contentDescription = "Header Logo: Fabler"
            )
            Spacer(Modifier.weight(1f))
            Image(
                painter = painterResource(id = R.drawable.round_face_36),
                contentDescription = "User Profile",
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewHeader() {
    Header {}
}
