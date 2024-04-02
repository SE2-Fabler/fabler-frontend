package com.se2.fabler

import androidx.compose.runtime.mutableStateOf
import com.se2.fabler.api.FablerRepository
import com.se2.fabler.api.FablerService
import com.se2.fabler.models.UserDataAll

class AppModel(
    // Logged in user's data
    // FIXME: Currently there's no way to refresh this!
    var loggedInUserData: UserDataAll
) {
    /* ===------------------------------------------------------------------------------------=== */
    // Navigation stack implementation
    /* ===------------------------------------------------------------------------------------=== */

    // Private nav stack, does not refresh UI
    private val viewStack = mutableListOf<Pair<String, Any?>>()

    // Private current view, writes will cause recompose
    private val currentViewMut = mutableStateOf("")

    // Public current view's data
    val currentViewData: Any? get() = viewStack.lastOrNull()?.second

    /**
     * Gets the current view at the top of the nav stack
     */
    val currentView: String get() = currentViewMut.value

    /**
     * Pushes a view onto the nav stack and change to it
     */
    fun pushView(name: String, data: Any? = null) {
        viewStack.add(Pair(name, data))
        currentViewMut.value = name
    }

    /**
     * Pops a view from the stack and change view to the top
     */
    fun popView() {
        viewStack.removeLast()
        currentViewMut.value = viewStack.last().first
    }

    /* ===------------------------------------------------------------------------------------=== */
    // Fabler API service and data repository adapter
    /* ===------------------------------------------------------------------------------------=== */

    val service: FablerService = FablerService()
    val repository: FablerRepository = FablerRepository(service)
}
