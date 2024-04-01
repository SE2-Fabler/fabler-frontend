package com.se2.fabler.models

data class UserDataAll(
    val user: UserData,
    val stories: List<BookData>,
    val bookmarks: List<BookData>
)
