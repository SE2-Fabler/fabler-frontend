package com.se2.fabler.api

import com.se2.fabler.models.BookData
import com.se2.fabler.models.CredentialsData
import com.se2.fabler.models.UserData
import com.se2.fabler.models.UserDataAll

interface IFablerService {
    suspend fun searchBooks(query: String, page: Int, itemsPerPage: Int): List<BookData>
    suspend fun searchUsers(query: String, page: Int, itemsPerPage: Int): List<UserData>
    fun getUserDataAll(userId: Int): UserDataAll
    suspend fun authUser(credential: CredentialsData): UserData?
    suspend fun registerUser(credential: CredentialsData, email: String)
    suspend fun getFollowers(userId: Int, page: Int, itemsPerPage: Int): List<UserData>
    suspend fun getFollowing(userId: Int, page: Int, itemsPerPage: Int) : List<UserData>
    suspend fun getBookmarked(userId: Int, page: Int, itemsPerPage: Int): List<BookData>
    suspend fun setFollow(userId: Int, followerID: Int, set: Boolean)
    suspend fun setBookmark(userId: Int, bookID: Int, set: Boolean)
    suspend fun deleteBook(bookID: Int)
}
