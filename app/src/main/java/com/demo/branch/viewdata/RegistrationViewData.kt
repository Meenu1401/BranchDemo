package com.demo.branch.viewdata

import com.demo.branch.di.ActivityScope
import com.demo.branch.user.UserManager
import javax.inject.Inject

@ActivityScope
class RegistrationViewData @Inject constructor(val userManager: UserManager) {

    private var username: String? = null
    private var password: String? = null

    fun updateUserData(username: String, password: String) {
        this.username = username
        this.password = password
    }


    fun registerUser() {
        assert(username != null)
        assert(password != null)
        userManager.registerUser(username!!)
    }
}
