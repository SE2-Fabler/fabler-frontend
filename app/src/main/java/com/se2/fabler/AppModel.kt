package com.se2.fabler

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.se2.fabler.api.FablerRepository
import com.se2.fabler.api.FablerService
import com.se2.fabler.models.BookData
import com.se2.fabler.models.UserData

class AppModel {
    /* ===------------------------------------------------------------------------------------=== */
    // Navigation stack implementation
    /* ===------------------------------------------------------------------------------------=== */

    // Private nav stack, does not refresh UI
    private val viewStack = mutableListOf<String>()

    // Private current view, writes will cause recompose
    private val currentViewMut = mutableStateOf("")

    /**
     * Gets the current view at the top of the nav stack
     */
    val currentView: String get() = currentViewMut.value

    /**
     * Pushes a view onto the nav stack and change to it
     */
    fun pushView(name: String) {
        viewStack.add(name)
        currentViewMut.value = name
    }

    /**
     * Pops a view from the stack and change view to the top
     */
    fun popView() {
        viewStack.removeLast()
        currentViewMut.value = viewStack.last()
    }

    /* ===------------------------------------------------------------------------------------=== */
    // Data source adapters and whatnot
    /* ===------------------------------------------------------------------------------------=== */

    private val dataSource: TestDataSource = TestDataSource()
    private val service: FablerService = FablerService()
    val repository: FablerRepository = FablerRepository(service)
    private val currentUserIdMut = mutableIntStateOf(-1)
    val currentUserId: Int get() = currentUserIdMut.value

    fun getUserCreations(userId: Int): List<BookData> {
        return dataSource.books // Returns the same list no matter what...
    }

    fun getUserBookmarks(userId: Int): List<BookData> {
        return dataSource.books // Returns the same list no matter what...
    }

    fun getUserProfile(userId: Int): UserData {
        return dataSource.userdata
    }
}
