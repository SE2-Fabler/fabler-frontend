package com.se2.fabler.api

import com.se2.fabler.models.BookData
import com.se2.fabler.models.CredentialsData
import com.se2.fabler.models.UserData
import com.se2.fabler.models.UserDataAll

interface IFablerService {
    fun searchBooks(query: String, page: Int, itemsPerPage: Int): List<BookData>
    fun searchUsers(query: String, page: Int, itemsPerPage: Int): List<UserData>
    fun getUserDataAll(userId: Int): UserDataAll
    fun authUser(credential: CredentialsData): UserData?
}
