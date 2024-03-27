package com.se2.fabler.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.se2.fabler.models.TabData
import com.se2.fabler.ui.components.CustomTabStrip
import com.se2.fabler.ui.components.SearchHeader

@Composable
fun SearchScreen(onSearchToggle: () -> Unit) {
    SearchHeader { onSearchToggle() }
    CustomTabStrip(
        listOf(
            TabData("Users") {
                UserScreen()
            },
            TabData("Stories") {
                StoryScreen()
            },
        )
    )
}

@Composable
@Preview(showBackground = true)
private fun PreviewSearchScreen() {
    Column {SearchScreen {}}
}
