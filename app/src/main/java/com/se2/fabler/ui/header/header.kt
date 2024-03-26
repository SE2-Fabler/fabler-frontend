package com.se2.fabler.ui.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.se2.fabler.R

@Preview
@Composable
fun Header(onSearchToggle: () -> Unit) {

    Surface(Modifier.fillMaxWidth()) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            Image(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "Search",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
                modifier = Modifier.padding(10.dp).clickable(onClick = onSearchToggle)
            )

            Spacer(Modifier.weight(1f))

            Text("Fabler", fontSize = 24.sp)

            Spacer(Modifier.weight(1f))

            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = "User",
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary),
                modifier = Modifier.padding(10.dp)
            )

        }

    }

}