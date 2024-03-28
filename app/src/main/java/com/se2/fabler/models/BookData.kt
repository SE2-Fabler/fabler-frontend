package com.se2.fabler.models

data class BookData(
    val id: Int,
    val title: String,
    val imageResId: Int,
    val author: String,
    val genre: String,
    val description: String,
    val bookmarks: Int,
    val bookmarked: Boolean,
)
