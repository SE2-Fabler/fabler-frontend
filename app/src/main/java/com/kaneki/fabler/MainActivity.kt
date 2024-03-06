package com.kaneki.fabler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kaneki.fabler.ui.header.Header
import com.kaneki.fabler.ui.theme.FablerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FablerTheme {

                Header()


            }
        }
    }
}

