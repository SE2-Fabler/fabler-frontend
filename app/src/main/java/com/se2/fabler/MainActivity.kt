package com.se2.fabler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.se2.fabler.ui.theme.FablerTheme
import com.se2.fabler.ui.views.HomeScreen
import com.se2.fabler.ui.views.SearchScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FablerTheme {
                Column {
                    var isSearch by remember { mutableStateOf(false) }
                    if (!isSearch) {
                        HomeScreen { isSearch = true }
                    } else {
                        SearchScreen { isSearch = false }
                    }
                }
            }
        }
    }
}
