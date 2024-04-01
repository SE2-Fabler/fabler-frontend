package com.se2.fabler.api

import com.se2.fabler.TestDataSource
import com.se2.fabler.models.BookData
import com.se2.fabler.models.UserData
import com.se2.fabler.models.UserDataAll

class FablerService {
    private val dataSource: TestDataSource = TestDataSource()

    fun searchBooks(query: String, page: Int, itemsPerPage: Int): List<BookData> {
        // Delay for 2 second to simulate network request
        Thread.sleep(1000)
        return if (page < 10) {
            List(itemsPerPage) { dataSource.books[0] }
        } else {
            listOf()
        }
    }

    fun searchUsers(query: String, page: Int, itemsPerPage: Int): List<UserData> {
        // Delay for 2 second to simulate network request
        Thread.sleep(1000)
        return if (page < 10) {
            return List(itemsPerPage){dataSource.userdata}
        } else {
            listOf()
        }
    }

    fun getUserDataAll(userId: Int): UserDataAll {
        // Delay for 1 second to simulate network request
        Thread.sleep(1000)
        return UserDataAll(dataSource.userdata, dataSource.books, dataSource.books)
    }
}