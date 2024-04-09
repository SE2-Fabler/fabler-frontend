package com.se2.fabler.api

import android.util.Log
import com.se2.fabler.TestDataSource
import com.se2.fabler.models.BookData
import com.se2.fabler.models.CredentialsData
import com.se2.fabler.models.UserData
import com.se2.fabler.models.UserDataAll
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TestFablerService : IFablerService {
    private val dataSource: TestDataSource = TestDataSource()

    override suspend fun searchBooks(query: String, page: Int, itemsPerPage: Int): List<BookData> {
        // Delay for 2 second to simulate network request
        withContext(Dispatchers.IO) {
            Thread.sleep(1000)
        }
        return if (page < 10) {
            List(itemsPerPage) { dataSource.books[0] }
        } else {
            listOf()
        }
    }

    override suspend fun searchUsers(query: String, page: Int, itemsPerPage: Int): List<UserData> {
        // Delay for 2 second to simulate network request
        Thread.sleep(1000)
        return if (page < 10) {
            return List(itemsPerPage){dataSource.otheruser}
        } else {
            listOf()
        }
    }

    override fun getUserDataAll(userId: Int): UserDataAll {
        // Delay for 1 second to simulate network request
        Thread.sleep(1000)
        Log.d("TestFablerService", "getUserDataAll: $userId")
        return if(userId == 1)
            UserDataAll(dataSource.userdata, dataSource.books, dataSource.books)
        else
            UserDataAll(dataSource.otheruser, dataSource.books, dataSource.books)
    }

    override suspend fun authUser(credential: CredentialsData): UserData? {
        // Delay for 1 second to simulate network request
        Thread.sleep(1000)
        return if(dataSource.userdata.username == credential.username)
            dataSource.userdata
        else
            null
    }

    override fun getFollowers(userId: Int, page: Int, itemsPerPage: Int): List<UserData> {
        return(listOf(dataSource.userdata, dataSource.otheruser))
    }

    override fun getFollowing(userId: Int, page: Int, itemsPerPage: Int): List<UserData> {
        return(listOf(dataSource.userdata, dataSource.otheruser))
    }
}
