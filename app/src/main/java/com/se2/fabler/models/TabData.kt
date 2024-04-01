package com.se2.fabler.models

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

class TabData(
    val title: String,
    val icon: ImageVector,
    val onSelected: () -> Unit = {},
    val content: @Composable () -> Unit
)
