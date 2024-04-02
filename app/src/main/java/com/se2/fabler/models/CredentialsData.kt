package com.se2.fabler.models

data class CredentialsData(
    val username: String = "",
    val password: String = "",
    val rememberMe: Boolean = false
) {
    fun isNotEmpty(): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }
}
