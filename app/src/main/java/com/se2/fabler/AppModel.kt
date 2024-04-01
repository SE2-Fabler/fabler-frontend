package com.se2.fabler

import androidx.compose.runtime.mutableStateOf
import com.se2.fabler.api.FablerRepository
import com.se2.fabler.api.FablerService
import com.se2.fabler.models.UserDataAll

class AppModel(
    // User data to display for ProfilePage
    var userToDisplayId: Int,
    // Logged in user's data
    // FIXME: Currently there's no way to refresh this!
    var loggedInUserData: UserDataAll
) {
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
    // Fabler API service and data repository adapter
    /* ===------------------------------------------------------------------------------------=== */

    val service: FablerService = FablerService()
    val repository: FablerRepository = FablerRepository(service)
}
