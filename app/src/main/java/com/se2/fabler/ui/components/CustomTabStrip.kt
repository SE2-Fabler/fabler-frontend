package com.se2.fabler.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.se2.fabler.R
import com.se2.fabler.models.TabData
import com.se2.fabler.ui.theme.AppColors.Companion.PRIMARY_COLOR
import com.se2.fabler.ui.theme.AppColors.Companion.PRIMARY_FONT_COLOR
import com.se2.fabler.ui.theme.AppColors.Companion.SECONDARY_COLOR

@Composable
fun CustomTabStrip(
    tabs: List<TabData>, onTabChange: (tab: TabData, index: Int) -> Unit = { _, _ -> }
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val stitchingImage = ImageBitmap.imageResource(R.drawable.book_stitching)
    Box {
        // This box draws the contents BELOW the tab view (shadowing)
        Box(
            Modifier.padding(0.dp, 110.dp, 0.dp, 0.dp)
        ) {
            tabs[selectedTab].content()
        }
        // This card provides the shadow that is cast upon the contents
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .height(120.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            ),
            colors = CardColors(
                Color.Transparent,
                Color.Transparent,
                Color.Transparent,
                Color.Transparent
            ),
            shape = RoundedCornerShape(0.dp)
        ) {
            // This canvas covers up the shadow of the parent card
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
                    .background(Color.Transparent)
            ) {
                drawRect(
                    color = Color.White,
                    size = Size(size.width, 115.dp.toPx()),
                )
            }
            // This column stacks the tab control + stitching image
            Column(Modifier.padding(0.dp, 15.dp, 0.dp, 0.dp)) {
                // The tab itself (rounded rectangle + selected text) is this component
                ElevatedCard(
                    modifier = Modifier
                        .padding(16.dp, 0.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 5.dp
                    ),
                    colors = CardColors(
                        SECONDARY_COLOR,
                        SECONDARY_COLOR,
                        SECONDARY_COLOR,
                        SECONDARY_COLOR
                    ),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        tabs.forEachIndexed { index, cardTab ->
                            val scale = 1f / (tabs.size - index)
                            Tab(
                                selected = (selectedTab == index),
                                onClick = {
                                    selectedTab = index
                                    tabs[index].onSelected()
                                    onTabChange(tabs[index], index)
                                },
                                modifier = Modifier
                                    .clip(RoundedCornerShape(18.dp))
                                    .background(if (selectedTab == index) PRIMARY_COLOR else SECONDARY_COLOR)
                                    .fillMaxWidth(scale),
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 8.dp)) { // Set the padding of the row
                                    Icon(
                                        painterResource(id = cardTab.icon),
                                        contentDescription = "Icon",
                                        tint = PRIMARY_FONT_COLOR,
                                        modifier = Modifier.size(36.dp).padding(0.dp, 4.dp, 0.dp, 4.dp) // Set the size of the icon
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        cardTab.title,
                                        color = PRIMARY_FONT_COLOR,
                                        // FIXME: No magic numbers!
                                        fontWeight = FontWeight(if (selectedTab == index) 700 else 400),
                                        fontSize = TextUnit(16.5f, TextUnitType.Sp)
                                    )
                                }
                            }
                        }
                    }
                }
                // Draw stitching image repeated on a canvas
                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 7.dp, 0.dp, 10.dp)
                        .background(Color.Transparent)
                ) {
                    val s = Size(stitchingImage.width.toFloat(), stitchingImage.height.toFloat())
                    val factor = 2f
                    val scaledSize = Size(s.width * factor, s.height * factor)
                    val stitchingLoc = Offset.Zero
                    val stitchingEnd = size.width
                    var stitchingStart = 0f
                    while (stitchingStart < stitchingEnd) {
                        drawScaledBitmap(
                            this,
                            stitchingImage,
                            Offset(stitchingStart, stitchingLoc.y),
                            scaledSize
                        )
                        stitchingStart += scaledSize.width
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun PreviewToggleComponent() {
    MaterialTheme {
        Column(Modifier.height(200.dp)) {
            CustomTabStrip(listOf(TabData("Tab 1", icon = R.drawable.baseline_menu_book_36) {
                Text("Tab 1 content")
            }, TabData("Tab 2", icon = R.drawable.baseline_bookmark_48) {
                Text("Tab 2 content")
            }, TabData("Tab 3", icon = R.drawable.baseline_send_36) {
                Text("Tab 3 content")
            })) { _, _ -> }
        }
    }
}
