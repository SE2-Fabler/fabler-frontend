package com.se2.fabler.models

data class UserData(
    val id: Int,
    val name: String,
    val email: String,
    val username: String,
    val profileImageResId: Int,
    val followers: Int,
    val following: Int,
    val bookmarkPrivacy: Boolean,
    val about: String,
    val location: String,
    val joined: String,
    val imFollowing: Boolean,
    val isFollowing: Boolean
)
