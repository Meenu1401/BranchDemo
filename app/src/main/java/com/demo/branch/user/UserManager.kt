package com.demo.branch.user

import com.demo.branch.storage.Storage
import javax.inject.Inject
import javax.inject.Singleton

private const val REGISTERED_USER_AUTH_TOKEN = "registered_user"

@Singleton
class UserManager @Inject constructor(
    private val storage: Storage,
    private val userComponentFactory: UserComponent.Factory
) {
    var userComponent: UserComponent? = null
        private set

    val authToken: String
        get() = storage.getString(REGISTERED_USER_AUTH_TOKEN)

    fun isUserRegistered() = storage.getString(REGISTERED_USER_AUTH_TOKEN).isNotEmpty()

    fun registerUser(authToken: String) {
        storage.setString(REGISTERED_USER_AUTH_TOKEN, authToken)
        userJustLoggedIn()
    }

    fun logout() {
        userComponent = null
    }

    private fun userJustLoggedIn() {
        userComponent = userComponentFactory.create()
    }
}
