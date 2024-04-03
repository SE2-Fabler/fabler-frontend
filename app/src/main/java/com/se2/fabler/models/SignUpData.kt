package com.se2.fabler.models

data class SignUpData(
    val name: String = "",
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
) {
    fun isNotEmpty(): Boolean {
        return name.isNotEmpty() && username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()
    }
}
