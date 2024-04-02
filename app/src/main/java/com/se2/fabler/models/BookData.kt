package com.se2.fabler.models

data class BookData(
    val id: Int,
    val title: String,
    val imageResId: Int,
    val author: String,
    val authorUserId: Int,
    val genre: String,
    val description: String,
    val private: Boolean,
    val bookmarks: Int,
    val bookmarked: Boolean,
)
