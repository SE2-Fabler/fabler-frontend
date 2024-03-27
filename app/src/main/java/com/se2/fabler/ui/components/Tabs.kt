package com.se2.fabler.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val TAB_PRIMARY_COLOR = Color(0xff6c2c01)
val TAB_SECONDARY_COLOR = Color(0xfff2ece5)

class CardTab(
    val title: String,
    val data: Any = Unit,
    val onSelected: () -> Unit = {},
    val content: @Composable () -> Unit
)

@Composable
fun ToggleComponent(
    tabs: List<CardTab>,
    onTabChange: (tab: CardTab, index: Int) -> Unit = { _, _ -> }
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    Row(
        modifier = Modifier
            .padding(8.dp)
            .background(TAB_SECONDARY_COLOR, RoundedCornerShape(20.dp))
            .padding(6.dp, 6.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        tabs.forEachIndexed { index, cardTab ->
            val scale = 1f / (tabs.size - index)
            val bgcolor = if (selectedTab == index) TAB_PRIMARY_COLOR else TAB_SECONDARY_COLOR
            val fgcolor = if (selectedTab == index) TAB_SECONDARY_COLOR else TAB_PRIMARY_COLOR
            Tab(
                text = { Text(cardTab.title, color = fgcolor) },
                selected = (selectedTab == index),
                onClick = {
                    selectedTab = index
                    tabs[index].onSelected()
                    onTabChange(tabs[index], index)
                },
                modifier = Modifier
                    .clip(RoundedCornerShape(18.dp))
                    .background(bgcolor)
                    .fillMaxWidth(scale)
            )
        }
    }
    tabs[selectedTab].content()
}

@Preview(showBackground = true)
@Composable
fun PreviewToggleComponent() {
    ToggleComponent(listOf(CardTab("Tab 1") {
        Text("Tab 1 content")
    }, CardTab("Tab 2") {
        Text("Tab 2 content")
    }, CardTab("Tab 3") {
        Text("Tab 3 content")
    })) { _, _ -> }
}
