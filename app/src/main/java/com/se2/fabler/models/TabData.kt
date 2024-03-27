package com.se2.fabler.models

import androidx.compose.runtime.Composable

class TabData(
    val title: String,
    val onSelected: () -> Unit = {},
    val content: @Composable () -> Unit
)
