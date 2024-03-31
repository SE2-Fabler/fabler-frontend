package com.se2.fabler.models

data class UserData(
    val id: Int,
    val name: String,
    val email: String,
    val username: String,
    val profileImageResId: Int,
    val followerCount: Int,
    val followingCount: Int,
    val stories: List<BookData>,
    val bookmarks: List<BookData>,
    val bookmarkPrivacy: Boolean,
    val about: String,
    val location: String,
    val joined: String,
    val following: Boolean,
    val isFollowing: Boolean
)
