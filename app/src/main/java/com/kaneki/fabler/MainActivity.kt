package com.kaneki.fabler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaneki.fabler.ui.components.CardTab
import com.kaneki.fabler.ui.components.TabbedCard
import com.kaneki.fabler.ui.header.Header
import com.kaneki.fabler.ui.theme.FablerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FablerTheme {

                Column {

                    Header()

                    var selectedString by remember { mutableStateOf("日本語") }

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
                    )
                    ) { tab, _ ->
                        selectedString = tab.title
                    }
                }


            }
        }
    }
}

