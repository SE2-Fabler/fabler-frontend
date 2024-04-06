package com.se2.fabler.models

data class UserData(
    val id: Int,
    val name: String,
    val email: String,
    val username: String,
    val profileImageResId: Int,
    val followerList: List<Int>,
    val followingList: List<Int>,
    val bookmarkPrivacy: Boolean,
    val about: String,
    val location: String,
    val joined: String,
    val following: Boolean,
    val isFollowing: Boolean,
    val creationList: List<Int>,
    val bookmarkList: List<Int>,
)
