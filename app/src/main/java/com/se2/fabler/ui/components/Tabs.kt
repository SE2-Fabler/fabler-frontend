package com.se2.fabler.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class CardTab(val title: String,
              val enabled: Boolean = true,
              val data: Any = Unit,
              val onSelected: () -> Unit = {},
              val content: @Composable () -> Unit
)

@Composable
fun TabbedCard(tabs: List<CardTab>, onTabChange: (tab: CardTab, index: Int) -> Unit = { _, _ -> }) {

    var selectedTab by remember { mutableIntStateOf(0) }

    TabRow(
        selectedTabIndex = selectedTab,
        containerColor = MaterialTheme.colorScheme.background,
        indicator = { SecondaryIndicator(  color = Color.Transparent) },
        divider = { Box(
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth(1f)
                .background(
                    Brush.verticalGradient(
                        listOf(MaterialTheme.colorScheme.background, Color.Transparent)
                    )
                )
        )
            HorizontalDivider(color = MaterialTheme.colorScheme.outline)
        }
    ) {
        tabs.forEachIndexed { index, cardTab ->
            if(cardTab.enabled) {
                Tab(
                    text = { Text(cardTab.title) },
                    selected = (selectedTab == index),
                    onClick = {
                        selectedTab = index
                        tabs[index].onSelected()
                        onTabChange(tabs[index], index)
                    },
                    selectedContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedContentColor = Color.Gray,
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(
                            color = if (selectedTab == index) MaterialTheme.colorScheme.primaryContainer else Color.Transparent,
                        )
                )
            }
        }
    }

    tabs[selectedTab].content()

}


@Preview
@Composable
private fun TabbedCardPreview() {

    var selectedString by remember { mutableStateOf("日本語") }

    Surface {
        Column {
            TabbedCard(listOf(
                CardTab("日本語") {
                    Text(
                        text = "こんにちは、世界！",
                        color = Color.DarkGray,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(15.dp)
                    )
                },
                CardTab("Deutsch") {
                    Text(
                        text = "Guten Morgen！",
                        color = Color.DarkGray,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(15.dp)
                    )
                },
                CardTab("English", enabled = false) {
                    Text(
                        text = "Hello, World！",
                        color = Color.DarkGray,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(15.dp)
                    )
                },
            ),
            ) { tab, _ ->
                selectedString = tab.title
            }

            Spacer(Modifier.height(15.dp))

            Text("You have selected: $selectedString")

        }
    }

}