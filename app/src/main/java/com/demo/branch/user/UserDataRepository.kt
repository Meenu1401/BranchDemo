package com.demo.branch.user

import javax.inject.Inject
import kotlin.random.Random
@LoggedUserScope
class UserDataRepository @Inject constructor(private val userManager: UserManager) {

    val username: String
        get() = userManager.authToken

    var unreadNotifications: Int = randomInt()

}

fun randomInt(): Int {
    return Random.nextInt(until = 100)
}
