package com.se2.fabler.api

import com.se2.fabler.TestDataSource
import com.se2.fabler.models.BookData
import com.se2.fabler.models.UserData

class FablerService {
    fun searchBooks(query: String, page: Int, itemsPerPage: Int): List<BookData> {
        // Delay for 1 second to simulate network request
        Thread.sleep(1000)
        return List(itemsPerPage){TestDataSource().books[0]}
    }

    fun searchUsers(query: String, page: Int, itemsPerPage: Int): List<UserData> {
        // Delay for 1 second to simulate network request
        Thread.sleep(1000)
        return List(itemsPerPage){TestDataSource().userdata}
    }
}